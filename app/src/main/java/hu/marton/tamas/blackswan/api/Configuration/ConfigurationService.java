package hu.marton.tamas.blackswan.api.Configuration;

import hu.marton.tamas.blackswan.api.Configuration.model.Configuration;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public interface ConfigurationService {

    @GET("/configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String apiKey);
}