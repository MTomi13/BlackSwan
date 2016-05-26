package hu.marton.tamas.blackswan.api;

import hu.marton.tamas.blackswan.api.Configuration.ConfigurationService;
import hu.marton.tamas.blackswan.api.Popular.PopularContentService;
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
    public PopularContentService createPopularService() {
        Retrofit.Builder builder = getRestAdapterBuilder(new OkHttpClient.Builder().addInterceptor(setupLogger()).build(), API_ENDPOINT);
        return builder.build().create(PopularContentService.class);
    }

    /**
     * @return ConfigurationService
     * create retrofit builder for ConfigurationService
     */
    public ConfigurationService createConfigurationService() {
        Retrofit.Builder builder = getRestAdapterBuilder(new OkHttpClient.Builder().addInterceptor(setupLogger()).build(), API_ENDPOINT);
        return builder.build().create(ConfigurationService.class);
    }

    /**
     * @param client
     * @param serviceEndpoint
     * @return Retrofit.Builder
     * setup Restadapter builder, with HttpClient, GsonConverter, baseUrl
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
