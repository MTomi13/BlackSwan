package hu.marton.tamas.movie.home;

import android.app.SearchManager;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.view.View;

import java.util.List;

import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.api.Configuration.model.ConfigurationResponseStore;
import hu.marton.tamas.movie.api.NoConnectivityException;
import hu.marton.tamas.movie.api.Popular.model.ContentType;
import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import hu.marton.tamas.movie.api.Popular.model.ResultWrapper;
import hu.marton.tamas.movie.home.HomeInteractorImpl.HomeInteractorListener;

/**
 * Created by tamas.marton on 09/03/2017.
 */

public class HomePresenterImpl implements HomePresenter, HomeInteractorListener {

    private ContentType contentType;
    private final HomeInteractorImpl homeInteractor;
    private final HomeView homeView;
    private final ConfigurationResponseStore configurationResponseStore;

    public HomePresenterImpl(HomeInteractorImpl homeInteractor, ConfigurationResponseStore configurationResponseStore, HomeView homeView) {
        this.homeInteractor = homeInteractor;
        this.homeView = homeView;
        this.configurationResponseStore = configurationResponseStore;
        homeInteractor.setHomeInteractorListener(this);
    }

    @Override
    public void startFetchContent(ContentType contentType) {
        this.contentType = contentType;
        homeView.showProgressView(View.VISIBLE);
        homeInteractor.startContentRequest(contentType);
    }

    /**
     * @param intent handle search intent
     */
    @Override
    public void handleSearchIntent(Intent intent, SearchRecentSuggestions searchRecentSuggestions, ContentType contentType) {
        this.contentType = contentType;
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchRecentSuggestions.saveRecentQuery(query, null);
            startQueryByContentType(query);
        }
    }

    /**
     * @param query query
     *              start search by content type
     */
    private void startQueryByContentType(String query) {
        homeInteractor.startSearchRequest(contentType, query);
    }

    @Override
    public void contentRequestSuccess(ResponseContent content) {
        homeView.showProgressView(View.GONE);
        homeView.onFetchContentSuccess(setResultsImageUrl(content));
    }

    /**
     * @param response response
     * @return ResponseContent
     * setup the responseContent image url's by the sizes, and base url from the configuration
     */
    private ResponseContent setResultsImageUrl(ResponseContent response) {
        response.setContentType(contentType);
        List<ResultWrapper> resultWrappers = response.getResultWrappers();
        String baseUlr = configurationResponseStore.getConfiguration().getImages().getBaseUrl();
        for (ResultWrapper resultWrapper : resultWrappers) {
            switch (contentType) {
                case MOVIES:
                case SERIES:
                    resultWrapper.setLogoImageUrl(baseUlr + configurationResponseStore.getConfiguration().getImages().getLogoSizes().get(3) + resultWrapper.getPosterPath());
                    resultWrapper.setBackDropImageUrl(baseUlr + configurationResponseStore.getConfiguration().getImages().getBackdropSizes().get(3) + resultWrapper.getBackdropPath());
                    break;
                case PEOPLE:
                    resultWrapper.setProfileImageUrl(baseUlr + configurationResponseStore.getConfiguration().getImages().getProfileSizes().get(3) + resultWrapper.getProfilePath());
                    break;
            }
        }
        response.setResultWrappers(resultWrappers);
        return response;
    }

    @Override
    public void contentRequestFailed(Throwable throwable) {
        if (throwable instanceof NoConnectivityException) {
            homeView.onFetchContentFailed(R.string.error_connection);
        } else {
            homeView.onFetchContentFailed(R.string.snackbar_text);
        }
    }
}