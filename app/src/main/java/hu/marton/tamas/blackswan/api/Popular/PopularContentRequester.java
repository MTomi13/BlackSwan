package hu.marton.tamas.blackswan.api.Popular;

import hu.marton.tamas.blackswan.BlackSwanApplication;
import hu.marton.tamas.blackswan.api.Popular.model.ContentType;
import hu.marton.tamas.blackswan.api.Popular.model.Response;
import retrofit2.Callback;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class PopularContentRequester {

    private PopularContentService popularContentService;

    public PopularContentRequester(PopularContentService popularContentService) {
        this.popularContentService = popularContentService;
    }

    public void getPopularContent(ContentType contentType, Callback<Response> callback) {
        switch (contentType) {
            case MOVIES:
                popularContentService.getPopularMovies(BlackSwanApplication.API_KEY).enqueue(callback);
                break;
            case SERIES:
                popularContentService.getPopularSeries(BlackSwanApplication.API_KEY).enqueue(callback);
                break;
            case PEOPLE:
                popularContentService.getPopularPeople(BlackSwanApplication.API_KEY).enqueue(callback);
                break;
        }
    }
}
