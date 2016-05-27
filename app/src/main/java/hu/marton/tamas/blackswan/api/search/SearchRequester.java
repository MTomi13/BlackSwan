package hu.marton.tamas.blackswan.api.search;

import hu.marton.tamas.blackswan.BlackSwanApplication;
import hu.marton.tamas.blackswan.api.Popular.model.ContentType;
import hu.marton.tamas.blackswan.api.Popular.model.ResponseContent;
import retrofit2.Callback;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class SearchRequester {

    private SearchService searchService;

    public SearchRequester(SearchService searchService) {
        this.searchService = searchService;
    }

    public void getSearchResult(ContentType contentType, String query, Callback<ResponseContent> callback) {
        switch (contentType) {
            case MOVIES:
                searchService.getSearchMovies(BlackSwanApplication.API_KEY, query).enqueue(callback);
                break;
            case SERIES:
                searchService.getSearchSeries(BlackSwanApplication.API_KEY, query).enqueue(callback);
                break;
            default:
                searchService.getSearchPeople(BlackSwanApplication.API_KEY, query).enqueue(callback);
                break;
        }
    }
}
