package hu.marton.tamas.movie.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import hu.marton.tamas.movie.MovieActivity;
import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.home.HomeActivity;
import hu.marton.tamas.movie.util.GeneralErrorHandler;
import hu.marton.tamas.movie.util.ViewHelper;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class SplashActivity extends MovieActivity implements SplashView {

    @BindView(R.id.progress_ring)
    ProgressBar progressRing;

    @Inject
    SplashPresenterImpl splashPresenter;

    @Inject
    SplashInteractorImpl splashInteractor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashPresenter.fetchConfiguration(splashInteractor);
    }

    @Override
    public void fetchConfigurationFailed(@StringRes int message) {
        GeneralErrorHandler.showErrorMessage(this, getString(message));
    }

    @Override
    public void fetchConfigurationSuccess() {
        startHomeActivity();
    }

    @Override
    public void showProgressView(int visibility) {
        ViewHelper.setVisibility(visibility, progressRing);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}