package hu.marton.tamas.movie.util;

import android.view.View;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class ViewHelper {

    /**
     * @param visibility visibility
     * @param views      views
     *                   setup the visibility for the views
     */
    public static void setVisibility(int visibility, View... views) {
        for (View view : views) {
            if (view != null) {
                view.setVisibility(visibility);
            }
        }
    }
}
