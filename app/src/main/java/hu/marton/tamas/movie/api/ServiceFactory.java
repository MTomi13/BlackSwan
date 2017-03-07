package hu.marton.tamas.movie.api;

import android.content.Context;
import android.net.ConnectivityManager;

import hu.marton.tamas.movie.api.Configuration.ConfigurationService;
import hu.marton.tamas.movie.api.Popular.PopularContentService;
import hu.marton.tamas.movie.api.search.SearchService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class ServiceFactory {

    private static final String API_ENDPOINT = "http://api.themoviedb.org/3/";

    /**
     * @return PopularContentService
     * create retrofit builder for PopularContentService
     */
    public PopularContentService createPopularService(Context context) {
        return getBuilder(context).build().create(PopularContentService.class);
    }

    /**
     * @return ConfigurationService
     * create retrofit builder for ConfigurationService
     */
    public ConfigurationService createConfigurationService(Context context) {
        return getBuilder(context).build().create(ConfigurationService.class);
    }

    /**
     * @return SearchService
     * create retrofit builder for SearchService
     */
    public SearchService createSearchService(Context context) {
        return getBuilder(context).build().create(SearchService.class);
    }

    private Retrofit.Builder getBuilder(Context context) {
        return getRestAdapterBuilder(new OkHttpClient.Builder()
                .addInterceptor(setupLogger())
                .addInterceptor(new NoConnectivityInterceptor((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)))
                .build(), API_ENDPOINT);
    }

    /**
     * @param client          client
     * @param serviceEndpoint serviceEndpoint
     * @return Retrofit.Builder
     * setup RestAdapter builder, with HttpClient, GsonConverter, baseUrl
     */
    private Retrofit.Builder getRestAdapterBuilder(OkHttpClient client, String serviceEndpoint) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(serviceEndpoint);
        builder.client(client);
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder;
    }

    /**
     * @return HttpLoggingInterceptor
     * setup LoggingInterceptor to log every Retrofit logs
     */
    private HttpLoggingInterceptor setupLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
