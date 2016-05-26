package hu.marton.tamas.blackswan.modules;

import dagger.Module;
import dagger.Provides;
import hu.marton.tamas.blackswan.BlackSwanActivity;
import hu.marton.tamas.blackswan.api.Configuration.ConfigurationRequester;
import hu.marton.tamas.blackswan.api.Configuration.model.ConfigurationResponseStore;
import hu.marton.tamas.blackswan.api.Popular.PopularContentRequester;
import hu.marton.tamas.blackswan.home.HomeActivity;
import hu.marton.tamas.blackswan.home.HomeActivityController;
import hu.marton.tamas.blackswan.splash.SplashActivity;
import hu.marton.tamas.blackswan.splash.SplashActivityController;

/**
 * Created by tamas.marton on 26/05/2016.
 */
@Module(
        injects = {
                BlackSwanActivity.class,
                HomeActivity.class,
                SplashActivity.class
        },
        addsTo = NetworkModule.class,
        library = true
)
public class ActivityModule {

    private final BlackSwanActivity blackSwanActivity;

    public ActivityModule(BlackSwanActivity blackSwanActivity) {
        this.blackSwanActivity = blackSwanActivity;
    }

    @Provides
    SplashActivityController provideSplashActivityController(ConfigurationRequester configurationRequester, ConfigurationResponseStore configurationResponseStore) {
        return new SplashActivityController(configurationRequester, configurationResponseStore);
    }

    @Provides
    HomeActivityController provideHomeActivityController(PopularContentRequester popularContentRequester, ConfigurationResponseStore configurationResponseStore) {
        return new HomeActivityController(popularContentRequester, configurationResponseStore);
    }
}
