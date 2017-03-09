package hu.marton.tamas.movie.splash;

import android.support.annotation.StringRes;

/**
 * Created by tamas.marton on 08/03/2017.
 */

public interface SplashView {

    void fetchConfigurationFailed(@StringRes int message);

    void fetchConfigurationSuccess();

    void showProgressRingVisibility(int visibility);
}
