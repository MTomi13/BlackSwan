package hu.marton.tamas.movie.api.Configuration;


import hu.marton.tamas.movie.MovieApplication;
import hu.marton.tamas.movie.api.Configuration.model.Configuration;
import retrofit2.Callback;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class ConfigurationRequester {

    private ConfigurationService configurationService;

    public ConfigurationRequester(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public void getConfiguration(Callback<Configuration> callback) {
        configurationService.getConfiguration(MovieApplication.API_KEY).enqueue(callback);
    }
}
