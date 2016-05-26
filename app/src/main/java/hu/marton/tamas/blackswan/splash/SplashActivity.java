package hu.marton.tamas.blackswan.splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import hu.marton.tamas.blackswan.BlackSwanActivity;
import hu.marton.tamas.blackswan.HomeActivity;
import hu.marton.tamas.blackswan.R;
import hu.marton.tamas.blackswan.util.ViewHelper;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class SplashActivity extends BlackSwanActivity implements SplashActivityController.ConfigurationRequestListener {

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

        }
    }

    private void setProgressRingVisibility(int visibility) {
        ViewHelper.setVisibility(visibility, findViewById(R.id.progress_ring));
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
    public void configurationFailed() {
    }

    private void startHomeActivity() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
