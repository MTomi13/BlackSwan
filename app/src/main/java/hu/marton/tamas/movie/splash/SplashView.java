package hu.marton.tamas.movie.splash;

import android.support.annotation.StringRes;

import hu.marton.tamas.movie.common.SplashHomeView;

/**
 * Created by tamas.marton on 08/03/2017.
 */

public interface SplashView extends SplashHomeView {

    void fetchConfigurationFailed(@StringRes int message);

    void fetchConfigurationSuccess();
}
