package hu.marton.tamas.blackswan.api.Configuration;


import hu.marton.tamas.blackswan.BlackSwanApplication;
import hu.marton.tamas.blackswan.api.Configuration.model.Configuration;
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
        configurationService.getConfiguration(BlackSwanApplication.API_KEY).enqueue(callback);
    }
}
