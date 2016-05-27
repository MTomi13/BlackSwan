package hu.marton.tamas.blackswan.api.search;

import hu.marton.tamas.blackswan.api.Popular.model.ResponseContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public interface SearchService {

    @GET("search/movie")
    Call<ResponseContent> getSearchMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("search/tv")
    Call<ResponseContent> getSearchSeries(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("search/person")
    Call<ResponseContent> getSearchPeople(@Query("api_key") String apiKey, @Query("query") String query);
}
