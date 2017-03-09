package hu.marton.tamas.movie.splash;

import android.view.View;

import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.api.NoConnectivityException;
import hu.marton.tamas.movie.splash.SplashInteractorImpl.SplashInteractorListener;

/**
 * Created by tamas.marton on 08/03/2017.
 */

public class SplashPresenterImpl implements SplashPresenter, SplashInteractorListener {

    private SplashView splashView;

    public SplashPresenterImpl(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void fetchConfiguration(SplashInteractorImpl splashInteractor) {
        splashInteractor.startConfigurationRequest(this);
        splashView.showProgressRingVisibility(View.VISIBLE);
    }

    @Override
    public void configurationSuccess() {
        splashView.showProgressRingVisibility(View.GONE);
        splashView.fetchConfigurationSuccess();
    }

    @Override
    public void configurationFailed(Throwable throwable) {
        if (throwable instanceof NoConnectivityException) {
            splashView.fetchConfigurationFailed(R.string.error_connection);
        } else {
            splashView.fetchConfigurationFailed(R.string.snackbar_text);
        }
    }
}
