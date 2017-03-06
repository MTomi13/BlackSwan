package hu.marton.tamas.movie.modules;

import dagger.Module;
import dagger.Provides;
import hu.marton.tamas.movie.api.Configuration.ConfigurationRequester;
import hu.marton.tamas.movie.api.Configuration.ConfigurationService;
import hu.marton.tamas.movie.api.Popular.PopularContentRequester;
import hu.marton.tamas.movie.api.Popular.PopularContentService;
import hu.marton.tamas.movie.api.search.SearchRequester;
import hu.marton.tamas.movie.api.search.SearchService;

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

    @Provides
    SearchRequester provideSearchRequester(SearchService searchService) {
        return new SearchRequester(searchService);
    }
}
