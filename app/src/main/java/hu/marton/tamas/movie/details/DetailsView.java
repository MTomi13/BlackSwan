package hu.marton.tamas.movie.details;

import android.support.annotation.StringRes;

/**
 * Created by tamas.marton on 10/03/2017.
 */

public interface DetailsView {

    void showImages(boolean isCollapsingImage, String imageURL);

    void showOriginalLanguage(@StringRes int label, String language);

    void showOverview(String overView);

    void showRate(@StringRes int label, String coverage, int count);

    void showToolbarTitle(String title);

    void showTitle(@StringRes int label, String title);

    void showReleaseDate(String date);

    void showOriginCountry(@StringRes int label, String date);
}