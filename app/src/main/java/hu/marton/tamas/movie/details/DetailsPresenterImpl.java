package hu.marton.tamas.movie.details;

import java.util.List;

import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.api.Popular.model.ResultWrapper;

/**
 * Created by tamas.marton on 10/03/2017.
 */

public class DetailsPresenterImpl implements DetailsPresenter {

    private DetailsView detailsView;
    private ResultWrapper resultWrapper;

    public DetailsPresenterImpl(DetailsView detailsView) {
        this.detailsView = detailsView;
    }

    @Override
    public void initPresenter(ResultWrapper resultWrapper) {
        this.resultWrapper = resultWrapper;
        setupTextViews();
        setupImages();
    }

    private void setupImages() {
        detailsView.showImages(true, resultWrapper.getBackDropImageUrl());
        detailsView.showImages(false, resultWrapper.getLogoImageUrl());
    }

    private void setupTextViews() {
        detailsView.showOriginalLanguage(R.string.original_language, resultWrapper.getOriginalLanguage());
        detailsView.showOverview(resultWrapper.getOverview());
        detailsView.showRate(R.string.rate, String.valueOf(resultWrapper.getVoteAverage()), resultWrapper.getVoteCount());
        setupViewsByContentType();
    }

    private void setupViewsByContentType() {
        switch (resultWrapper.getContentType()) {
            case MOVIES:
                detailsView.showToolbarTitle(resultWrapper.getTitle());
                detailsView.showTitle(R.string.original_title, resultWrapper.getOriginalTitle());
                detailsView.showReleaseDate(resultWrapper.getReleaseDate());
                break;
            case SERIES:
                detailsView.showToolbarTitle(resultWrapper.getName());
                setupOriginCountryForSeries(resultWrapper);
                detailsView.showTitle(R.string.original_title, resultWrapper.getOriginalName());
                detailsView.showReleaseDate(resultWrapper.getFirstAirDate());
                break;
        }
    }

    private void setupOriginCountryForSeries(ResultWrapper resultWrapper) {
        List<String> originCountry = resultWrapper.getOriginCountry();
        if (originCountry.size() != 0) {
            detailsView.showOriginCountry(R.string.origin_country, originCountry.get(0));
        }
    }
}