package hu.marton.tamas.blackswan.details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import hu.marton.tamas.blackswan.BlackSwanActivity;
import hu.marton.tamas.blackswan.R;
import hu.marton.tamas.blackswan.api.Popular.model.ResultWrapper;
import hu.marton.tamas.blackswan.util.ViewHelper;

/**
 * Created by tamas.marton on 27/05/2016.
 */
public class DetailsActivity extends BlackSwanActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupToolbar();

        ResultWrapper resultWrapper = getIntent().getParcelableExtra(ResultWrapper.class.getName());
        setupImages(resultWrapper, ViewHelper.getImageOptions());
        setupTextViews(resultWrapper);
    }

    /**
     * setup the "back button" functionality for the toolbar
     */
    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @param resultWrapper resultWrapper
     * @param imageOptions  imageOptions
     *                      setup image in the toolbar, and the poster image
     */
    private void setupImages(ResultWrapper resultWrapper, DisplayImageOptions imageOptions) {
        ImageLoader.getInstance().displayImage(resultWrapper.getBackDropImageUrl(), ((ImageView) findViewById(R.id.collapsing_image)), imageOptions, new SimpleImageLoadingListener());
        ImageLoader.getInstance().displayImage(resultWrapper.getLogoImageUrl(), ((ImageView) findViewById(R.id.poster_imageview)), imageOptions, new SimpleImageLoadingListener());
    }

    /**
     * @param resultWrapper resultWrapper
     *                      setup the textViews
     */
    private void setupTextViews(ResultWrapper resultWrapper) {
        ((TextView) findViewById(R.id.original_language)).setText(getResources().getString(R.string.original_language, resultWrapper.getOriginalLanguage()));
        ((TextView) findViewById(R.id.overview)).setText(resultWrapper.getOverview());
        ((TextView) findViewById(R.id.rate)).setText(getResources().getString(R.string.rate, String.valueOf(resultWrapper.getVoteAverage()), resultWrapper.getVoteCount()));
        setupViewsByContentType(resultWrapper);
    }

    /**
     * @param resultWrapper resultWrapper
     *                      setup the views by contentType. There could be differences between Movies and Series.
     */
    private void setupViewsByContentType(ResultWrapper resultWrapper) {
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        TextView originTitle = (TextView) findViewById(R.id.original_title);
        TextView releaseDate = (TextView) findViewById(R.id.release_date);
        switch (resultWrapper.getContentType()) {
            case MOVIES:
                collapsingToolbar.setTitle(resultWrapper.getTitle());
                originTitle.setText(getResources().getString(R.string.original_title, resultWrapper.getOriginalTitle()));
                releaseDate.setText(resultWrapper.getReleaseDate());
                break;
            case SERIES:
                collapsingToolbar.setTitle(resultWrapper.getName());
                ((TextView) findViewById(R.id.origin_country)).setText(getResources().getString(R.string.origin_country, resultWrapper.getOriginCountry().get(0)));
                originTitle.setText(getResources().getString(R.string.original_title, resultWrapper.getOriginalName()));
                releaseDate.setText(resultWrapper.getFirstAirDate());
                break;
        }
    }
}
