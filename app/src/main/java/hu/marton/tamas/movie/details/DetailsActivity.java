package hu.marton.tamas.movie.details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hu.marton.tamas.movie.MovieActivity;
import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.api.Popular.model.ResultWrapper;

/**
 * Created by tamas.marton on 27/05/2016.
 */
public class DetailsActivity extends MovieActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupToolbar();

        ResultWrapper resultWrapper = getIntent().getParcelableExtra(ResultWrapper.class.getName());
        setupImagesView(resultWrapper);
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
     */
    private void setupImagesView(ResultWrapper resultWrapper) {
        setupImages(((ImageView) findViewById(R.id.collapsing_image)), resultWrapper.getBackDropImageUrl());
        setupImages(((ImageView) findViewById(R.id.poster_imageview)), resultWrapper.getLogoImageUrl());
    }

    private void setupImages(ImageView imageView, String url) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_error)
                .into(imageView);
        Picasso.with(this).setIndicatorsEnabled(true);
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
                setupOriginCountryForSeries(resultWrapper);
                originTitle.setText(getResources().getString(R.string.original_title, resultWrapper.getOriginalName()));
                releaseDate.setText(resultWrapper.getFirstAirDate());
                break;
        }
    }

    private void setupOriginCountryForSeries(ResultWrapper resultWrapper) {
        List<String> originCountry = resultWrapper.getOriginCountry();
        if (originCountry.size() != 0) {
            ((TextView) findViewById(R.id.origin_country)).setText(getResources().getString(R.string.origin_country, originCountry.get(0)));
        }
    }
}
