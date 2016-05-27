package hu.marton.tamas.blackswan.api.Popular;

import hu.marton.tamas.blackswan.BlackSwanApplication;
import hu.marton.tamas.blackswan.api.Popular.model.ContentType;
import hu.marton.tamas.blackswan.api.Popular.model.ResponseContent;
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
                popularContentService.getPopularMovies(BlackSwanApplication.API_KEY).enqueue(callback);
                break;
            case SERIES:
                popularContentService.getPopularSeries(BlackSwanApplication.API_KEY).enqueue(callback);
                break;
            default:
                popularContentService.getPopularPeople(BlackSwanApplication.API_KEY).enqueue(callback);
                break;
        }
    }
}
