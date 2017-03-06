package hu.marton.tamas.movie.api.Popular;

import hu.marton.tamas.movie.MovieApplication;
import hu.marton.tamas.movie.api.Popular.model.ContentType;
import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import retrofit2.Callback;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class PopularContentRequester {

    private PopularContentService popularContentService;

    public PopularContentRequester(PopularContentService popularContentService) {
        this.popularContentService = popularContentService;
    }

    /**
     * @param contentType ContentType
     * @param callback    Callback<ResponseContent>
     *                    start the content request by the contentType
     */
    public void getPopularContent(ContentType contentType, Callback<ResponseContent> callback) {
        switch (contentType) {
            case MOVIES:
                popularContentService.getPopularMovies(MovieApplication.API_KEY).enqueue(callback);
                break;
            case SERIES:
                popularContentService.getPopularSeries(MovieApplication.API_KEY).enqueue(callback);
                break;
            default:
                popularContentService.getPopularPeople(MovieApplication.API_KEY).enqueue(callback);
                break;
        }
    }
}
