package hu.marton.tamas.movie.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.marton.tamas.movie.api.Configuration.ConfigurationService;
import hu.marton.tamas.movie.api.Configuration.model.ConfigurationResponseStore;
import hu.marton.tamas.movie.api.Popular.PopularContentService;
import hu.marton.tamas.movie.api.ServiceFactory;
import hu.marton.tamas.movie.api.search.SearchService;

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
        return new ServiceFactory().createConfigurationService(application.getApplicationContext());
    }

    @Provides
    @Singleton
    PopularContentService providePopularService() {
        return new ServiceFactory().createPopularService(application.getApplicationContext());
    }

    @Provides
    @Singleton
    SearchService provideSearchService() {
        return new ServiceFactory().createSearchService(application.getApplicationContext());
    }

    @Provides
    @Singleton
    ConfigurationResponseStore provideConfigurationResponseStore() {
        return new ConfigurationResponseStore();
    }
}
