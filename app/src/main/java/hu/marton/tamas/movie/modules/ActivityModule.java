package hu.marton.tamas.movie.modules;

import dagger.Module;
import dagger.Provides;
import hu.marton.tamas.movie.MovieActivity;
import hu.marton.tamas.movie.api.Configuration.ConfigurationRequester;
import hu.marton.tamas.movie.api.Configuration.model.ConfigurationResponseStore;
import hu.marton.tamas.movie.api.Popular.PopularContentRequester;
import hu.marton.tamas.movie.api.search.SearchRequester;
import hu.marton.tamas.movie.details.DetailsActivity;
import hu.marton.tamas.movie.details.DetailsPresenterImpl;
import hu.marton.tamas.movie.details.DetailsView;
import hu.marton.tamas.movie.home.HomeActivity;
import hu.marton.tamas.movie.home.HomeInteractorImpl;
import hu.marton.tamas.movie.home.HomePresenterImpl;
import hu.marton.tamas.movie.home.HomeView;
import hu.marton.tamas.movie.splash.SplashActivity;
import hu.marton.tamas.movie.splash.SplashInteractorImpl;
import hu.marton.tamas.movie.splash.SplashPresenterImpl;
import hu.marton.tamas.movie.splash.SplashView;

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
    SplashPresenterImpl provideSplashPresenterImpl(SplashView splashView) {
        return new SplashPresenterImpl(splashView);
    }

    @Provides
    SplashInteractorImpl provideSplashActivityController(ConfigurationRequester configurationRequester, ConfigurationResponseStore configurationResponseStore) {
        return new SplashInteractorImpl(configurationRequester, configurationResponseStore);
    }

    @Provides
    HomePresenterImpl provideHomePresenterImpl(HomeInteractorImpl homeInteractor, ConfigurationResponseStore configurationResponseStore, HomeView homeView) {
        return new HomePresenterImpl(homeInteractor, configurationResponseStore, homeView);
    }

    @Provides
    HomeInteractorImpl provideHomeActivityController(PopularContentRequester popularContentRequester, SearchRequester searchRequester) {
        return new HomeInteractorImpl(popularContentRequester, searchRequester);
    }

    @Provides
    DetailsPresenterImpl provideDetailsPresenterImpl(DetailsView detailsView) {
        return new DetailsPresenterImpl(detailsView);
    }

    @Provides
    SplashView provideSplashView() {
        return (SplashView) movieActivity;
    }

    @Provides
    HomeView provideHomeView() {
        return (HomeView) movieActivity;
    }

    @Provides
    DetailsView provideDetailsView() {
        return (DetailsView) movieActivity;
    }
}
