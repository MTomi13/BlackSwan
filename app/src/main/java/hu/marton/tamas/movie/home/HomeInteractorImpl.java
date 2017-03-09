package hu.marton.tamas.movie.home;

import hu.marton.tamas.movie.api.Popular.PopularContentRequester;
import hu.marton.tamas.movie.api.Popular.model.ContentType;
import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import hu.marton.tamas.movie.api.search.SearchRequester;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tamas.marton on 26/05/2016.
 * HomeInteractorImpl to manage the network communication and callback to the activity
 */
public class HomeInteractorImpl implements HomeInteractor, Callback<ResponseContent> {

    private final PopularContentRequester popularContentRequester;
    private final SearchRequester searchRequester;

    private HomeInteractorListener homeInteractorListener;

    public HomeInteractorImpl(PopularContentRequester popularContentRequester, SearchRequester searchRequester) {
        this.popularContentRequester = popularContentRequester;
        this.searchRequester = searchRequester;
    }

    /**
     * @param contentType contentType
     *                    start the content request
     */
    @Override
    public void startContentRequest(ContentType contentType) {
        popularContentRequester.getPopularContent(contentType, this);
    }

    /**
     * @param contentType contentType
     * @param query       query
     *                    start the search request by query
     */
    @Override
    public void startSearchRequest(ContentType contentType, String query) {
        searchRequester.getSearchResult(contentType, query, this);
    }

    @Override
    public void setHomeInteractorListener(HomeInteractorListener homeInteractorListener) {
        this.homeInteractorListener = homeInteractorListener;
    }

    @Override
    public void onResponse(Call<ResponseContent> call, Response<ResponseContent> response) {
        homeInteractorListener.contentRequestSuccess(response.body());
    }

    @Override
    public void onFailure(Call<ResponseContent> call, Throwable throwable) {
        homeInteractorListener.contentRequestFailed(throwable);
    }

    interface HomeInteractorListener {

        void contentRequestSuccess(ResponseContent content);

        void contentRequestFailed(Throwable throwable);
    }
}