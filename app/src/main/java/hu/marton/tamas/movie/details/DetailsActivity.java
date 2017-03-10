package hu.marton.tamas.movie.details;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import hu.marton.tamas.movie.MovieActivity;
import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.api.Popular.model.ResultWrapper;

/**
 * Created by tamas.marton on 27/05/2016.
 */
public class DetailsActivity extends MovieActivity implements DetailsView {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.collapsing_image)
    ImageView collapsingImage;

    @BindView(R.id.poster_imageview)
    ImageView posterImage;

    @BindView(R.id.original_language)
    TextView originalLangTextView;

    @BindView(R.id.overview)
    TextView overViewTextView;

    @BindView(R.id.rate)
    TextView rateTextView;

    @BindView(R.id.original_title)
    TextView titleTextView;

    @BindView(R.id.release_date)
    TextView releaseDateTextView;

    @BindView(R.id.origin_country)
    TextView originCountryTextView;

    @Inject
    DetailsPresenterImpl detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupToolbar();
        detailsPresenter.initPresenter(getIntent().<ResultWrapper>getParcelableExtra(ResultWrapper.class.getName()));
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

    @Override
    public void showImages(boolean isCollapsingImage, String imageURL) {
        if (isCollapsingImage) {
            setupImages(collapsingImage, imageURL);
        } else {
            setupImages(posterImage, imageURL);
        }
    }

    private void setupImages(ImageView imageView, String url) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_error)
                .into(imageView);
        Picasso.with(this).setIndicatorsEnabled(true);
    }

    @Override
    public void showOriginalLanguage(@StringRes int label, String language) {
        originalLangTextView.setText(getString(label, language));
    }

    @Override
    public void showOverview(String overView) {
        overViewTextView.setText(overView);
    }

    @Override
    public void showRate(@StringRes int label, String coverage, int count) {
        rateTextView.setText(getString(label, coverage, count));
    }

    @Override
    public void showToolbarTitle(String title) {
        collapsingToolbar.setTitle(title);
    }

    @Override
    public void showTitle(@StringRes int label, String title) {
        titleTextView.setText(getString(label, title));
    }

    @Override
    public void showReleaseDate(String date) {
        releaseDateTextView.setText(date);
    }

    @Override
    public void showOriginCountry(@StringRes int label, String date) {
        originCountryTextView.setText(getString(label, date));
    }
}