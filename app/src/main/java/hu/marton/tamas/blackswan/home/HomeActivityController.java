package hu.marton.tamas.blackswan.home;

import java.util.List;

import hu.marton.tamas.blackswan.api.Configuration.model.ConfigurationResponseStore;
import hu.marton.tamas.blackswan.api.Popular.PopularContentRequester;
import hu.marton.tamas.blackswan.api.Popular.model.ContentType;
import hu.marton.tamas.blackswan.api.Popular.model.ResponseContent;
import hu.marton.tamas.blackswan.api.Popular.model.Result;
import hu.marton.tamas.blackswan.api.search.SearchRequester;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class HomeActivityController implements Callback<ResponseContent> {

    private final PopularContentRequester popularContentRequester;
    private final SearchRequester searchRequester;
    private final ConfigurationResponseStore configurationResponseStore;

    private ContentRequestListener contentRequestListener;
    private ContentType contentType;

    public HomeActivityController(PopularContentRequester popularContentRequester, SearchRequester searchRequester, ConfigurationResponseStore configurationResponseStore) {
        this.popularContentRequester = popularContentRequester;
        this.searchRequester = searchRequester;
        this.configurationResponseStore = configurationResponseStore;
    }

    public void startContentRequest(ContentType contentType) {
        this.contentType = contentType;
        popularContentRequester.getPopularContent(contentType, this);
    }

    public void startSearchRequest(ContentType contentType, String query) {
        this.contentType = contentType;
        searchRequester.getSearchResult(contentType, query, this);
    }

    public void setContentRequestListener(ContentRequestListener contentRequestListener) {
        this.contentRequestListener = contentRequestListener;
    }

    @Override
    public void onResponse(Call<ResponseContent> call, Response<ResponseContent> response) {
        contentRequestListener.contentRequestSuccess(setResultsImageUrl(response));
    }

    @Override
    public void onFailure(Call<ResponseContent> call, Throwable throwable) {
        contentRequestListener.contentRequestFailed(throwable);
    }

    private ResponseContent setResultsImageUrl(Response<ResponseContent> response) {
        response.body().setContentType(contentType);
        List<Result> results = response.body().getResults();
        String baseUlr = configurationResponseStore.getConfiguration().getImages().getBaseUrl();
        for (Result result : results) {
            switch (contentType) {
                case MOVIES:
                case SERIES:
                    result.setLogoImageUrl(baseUlr + configurationResponseStore.getConfiguration().getImages().getLogoSizes().get(3) + result.getPosterPath());
                    break;
                case PEOPLE:
                    result.setProfileImageUrl(baseUlr + configurationResponseStore.getConfiguration().getImages().getProfileSizes().get(3) + result.getProfilePath());
                    break;
            }
        }
        response.body().setResults(results);
        return response.body();
    }

    public interface ContentRequestListener {

        void contentRequestSuccess(ResponseContent content);

        void contentRequestFailed(Throwable throwable);
    }
}
