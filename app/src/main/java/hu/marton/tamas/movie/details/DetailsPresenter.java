package hu.marton.tamas.movie.details;

import hu.marton.tamas.movie.api.Popular.model.ResultWrapper;

/**
 * Created by tamas.marton on 10/03/2017.
 */

interface DetailsPresenter {

    void initPresenter(ResultWrapper resultWrapper);
}