package hu.marton.tamas.movie.home;

import android.support.annotation.StringRes;

import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import hu.marton.tamas.movie.common.SplashHomeView;

/**
 * Created by tamas.marton on 09/03/2017.
 */

public interface HomeView extends SplashHomeView {

    void onFetchContentSuccess(ResponseContent responseContent);

    void onFetchContentFailed(@StringRes int errorMessage);
}