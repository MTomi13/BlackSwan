package hu.marton.tamas.movie.api.search;

import hu.marton.tamas.movie.MovieApplication;
import hu.marton.tamas.movie.api.Popular.model.ContentType;
import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import retrofit2.Callback;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class SearchRequester {

    private SearchService searchService;

    public SearchRequester(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * @param contentType ContentType
     * @param query       String
     * @param callback    Callback<ResponseContent>
     *                    start the search request by the contentType
     */
    public void getSearchResult(ContentType contentType, String query, Callback<ResponseContent> callback) {
        switch (contentType) {
            case MOVIES:
                searchService.getSearchMovies(MovieApplication.API_KEY, query).enqueue(callback);
                break;
            case SERIES:
                searchService.getSearchSeries(MovieApplication.API_KEY, query).enqueue(callback);
                break;
            default:
                searchService.getSearchPeople(MovieApplication.API_KEY, query).enqueue(callback);
                break;
        }
    }
}
