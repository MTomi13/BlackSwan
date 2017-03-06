package hu.marton.tamas.movie.modules;

import dagger.Module;
import dagger.Provides;
import hu.marton.tamas.movie.MovieActivity;
import hu.marton.tamas.movie.api.Configuration.ConfigurationRequester;
import hu.marton.tamas.movie.api.Configuration.model.ConfigurationResponseStore;
import hu.marton.tamas.movie.api.Popular.PopularContentRequester;
import hu.marton.tamas.movie.api.search.SearchRequester;
import hu.marton.tamas.movie.details.DetailsActivity;
import hu.marton.tamas.movie.home.HomeActivity;
import hu.marton.tamas.movie.home.HomeActivityController;
import hu.marton.tamas.movie.splash.SplashActivity;
import hu.marton.tamas.movie.splash.SplashActivityController;

/**
 * Created by tamas.marton on 26/05/2016.
 */
@Module(
        injects = {
                MovieActivity.class,
                HomeActivity.class,
                SplashActivity.class,
                DetailsActivity.class
        },
        addsTo = NetworkModule.class,
        library = true
)
public class ActivityModule {

    private final MovieActivity movieActivity;

    public ActivityModule(MovieActivity movieActivity) {
        this.movieActivity = movieActivity;
    }

    @Provides
    SplashActivityController provideSplashActivityController(ConfigurationRequester configurationRequester, ConfigurationResponseStore configurationResponseStore) {
        return new SplashActivityController(configurationRequester, configurationResponseStore);
    }

    @Provides
    HomeActivityController provideHomeActivityController(PopularContentRequester popularContentRequester, SearchRequester searchRequester, ConfigurationResponseStore configurationResponseStore) {
        return new HomeActivityController(popularContentRequester, searchRequester, configurationResponseStore);
    }
}
