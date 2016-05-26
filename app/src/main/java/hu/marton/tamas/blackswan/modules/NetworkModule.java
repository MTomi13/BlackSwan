package hu.marton.tamas.blackswan.modules;

import dagger.Module;
import dagger.Provides;
import hu.marton.tamas.blackswan.api.Configuration.ConfigurationRequester;
import hu.marton.tamas.blackswan.api.Configuration.ConfigurationService;
import hu.marton.tamas.blackswan.api.Popular.PopularContentRequester;
import hu.marton.tamas.blackswan.api.Popular.PopularContentService;

/**
 * Created by tamas.marton on 26/05/2016.
 */
@Module(
        includes = {
                ApplicationModule.class,
        },
        library = true
)
public class NetworkModule {

    @Provides
    ConfigurationRequester provideConfigurationRequester(ConfigurationService configurationService) {
        return new ConfigurationRequester(configurationService);
    }

    @Provides
    PopularContentRequester providePopularContentRequester(PopularContentService popularContentService) {
        return new PopularContentRequester(popularContentService);
    }
}
