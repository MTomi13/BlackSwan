package hu.marton.tamas.movie.api.Popular;

import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public interface PopularContentService {

    @GET("movie/popular")
    Call<ResponseContent> getPopularMovies(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<ResponseContent> getPopularSeries(@Query("api_key") String apiKey);

    @GET("person/popular")
    Call<ResponseContent> getPopularPeople(@Query("api_key") String apiKey);
}
