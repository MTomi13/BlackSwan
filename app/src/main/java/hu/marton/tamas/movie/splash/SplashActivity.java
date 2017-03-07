package hu.marton.tamas.movie.splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
public class SplashActivity extends MovieActivity implements SplashActivityController.ConfigurationRequestListener {

    @BindView(R.id.progress_ring)
    ProgressBar progressRing;

    @Inject
    SplashActivityController splashActivityController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (isNetworkAvailable()) {
            splashActivityController.startConfigurationRequest(this);
            setProgressRingVisibility(View.VISIBLE);
        } else {
            GeneralErrorHandler.showErrorMessage(this, getString(R.string.snackbar_text_internet));
        }
    }

    private void setProgressRingVisibility(int visibility) {
        ViewHelper.setVisibility(visibility, progressRing);
    }

    /**
     * @return boolean
     * internet connection checker method
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void configurationSuccess() {
        setProgressRingVisibility(View.GONE);
        startHomeActivity();
    }

    @Override
    public void configurationFailed(Throwable throwable) {
        GeneralErrorHandler.showErrorMessage(this, getString(R.string.snackbar_text));
    }

    private void startHomeActivity() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
