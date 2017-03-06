package hu.marton.tamas.movie.splash;


import hu.marton.tamas.movie.api.Configuration.ConfigurationRequester;
import hu.marton.tamas.movie.api.Configuration.model.Configuration;
import hu.marton.tamas.movie.api.Configuration.model.ConfigurationResponseStore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tamas.marton on 26/05/2016.
 * Controller for the SPlashActivity to handle the configuration request, save the result and callback to the activity
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
        saveConfiguration(response);
        configurationRequestListener.configurationSuccess();
    }

    /**
     * @param response Response<Configuration>
     *                 save the configuration into the memory
     */
    private void saveConfiguration(Response<Configuration> response) {
        configurationResponseStore.setConfiguration(response.body());
    }

    @Override
    public void onFailure(Call<Configuration> call, Throwable throwable) {
        configurationRequestListener.configurationFailed(throwable);
    }

    public interface ConfigurationRequestListener {

        void configurationSuccess();

        void configurationFailed(Throwable throwable);
    }
}
