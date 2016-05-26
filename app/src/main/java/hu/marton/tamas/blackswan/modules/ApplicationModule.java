package hu.marton.tamas.blackswan.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.marton.tamas.blackswan.api.Configuration.ConfigurationService;
import hu.marton.tamas.blackswan.api.Configuration.model.ConfigurationResponseStore;
import hu.marton.tamas.blackswan.api.Popular.PopularContentService;
import hu.marton.tamas.blackswan.api.ServiceFactory;

/**
 * Created by tamas.marton on 26/05/2016.
 */
@Module(
        library = true
)
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ConfigurationService provideConfigurationService() {
        return new ServiceFactory().createConfigurationService();
    }

    @Provides
    @Singleton
    PopularContentService providePopularService() {
        return new ServiceFactory().createPopularService();
    }

    @Provides
    @Singleton
    ConfigurationResponseStore provideConfigurationResponseStore() {
        return new ConfigurationResponseStore();
    }
}
