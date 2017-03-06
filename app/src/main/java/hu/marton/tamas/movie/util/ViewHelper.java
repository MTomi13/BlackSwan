package hu.marton.tamas.movie.util;

import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import hu.marton.tamas.movie.R;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class ViewHelper {

    /**
     * @param visibility visibility
     * @param views  views
     * setup the visibility for the views
     */
    public static void setVisibility(int visibility, View... views) {
        for (View view : views) {
            if (view != null) {
                view.setVisibility(visibility);
            }
        }
    }

    /**
     * @return DisplayImageOptions
     * setup the ImageOptions
     */
    public static DisplayImageOptions getImageOptions() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .showImageOnLoading(R.drawable.placeholder)
                .showImageForEmptyUri(R.drawable.no_image)
                .displayer(new SimpleBitmapDisplayer())
                .build();
        return options;
    }
}
