package hu.marton.tamas.blackswan.splash;


import hu.marton.tamas.blackswan.api.Configuration.ConfigurationRequester;
import hu.marton.tamas.blackswan.api.Configuration.model.Configuration;
import hu.marton.tamas.blackswan.api.Configuration.model.ConfigurationResponseStore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class SplashActivityController implements Callback<Configuration> {

    private ConfigurationRequester configurationRequester;
    private ConfigurationResponseStore configurationResponseStore;
    private ConfigurationRequestListener configurationRequestListener;

    public SplashActivityController(ConfigurationRequester configurationRequester, ConfigurationResponseStore configurationResponseStore) {
        this.configurationRequester = configurationRequester;
        this.configurationResponseStore = configurationResponseStore;
    }

    public void startConfigurationRequest(ConfigurationRequestListener configurationRequestListener) {
        this.configurationRequestListener = configurationRequestListener;
        configurationRequester.getConfiguration(this);
    }

    @Override
    public void onResponse(Call<Configuration> call, Response<Configuration> response) {
        configurationResponseStore.setConfiguration(response.body());
        configurationRequestListener.configurationSuccess();
    }

    @Override
    public void onFailure(Call<Configuration> call, Throwable throwable) {
        configurationRequestListener.configurationFailed();
    }

    public interface ConfigurationRequestListener {

        void configurationSuccess();

        void configurationFailed();
    }
}
