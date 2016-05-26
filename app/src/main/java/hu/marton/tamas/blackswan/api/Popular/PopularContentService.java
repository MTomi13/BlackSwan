package hu.marton.tamas.blackswan.api.Popular;

import hu.marton.tamas.blackswan.api.Popular.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public interface PopularContentService {

    @GET("/movie/popular")
    Call<Response> getPopularMovies(@Query("api_key") String apiKey);

    @GET("/tv/popular")
    Call<Response> getPopularSeries(@Query("api_key") String apiKey);

    @GET("/person/popular")
    Call<Response> getPopularPeople(@Query("api_key") String apiKey);
}
