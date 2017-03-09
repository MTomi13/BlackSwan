package hu.marton.tamas.movie.home;

import hu.marton.tamas.movie.api.Popular.model.ContentType;

/**
 * Created by tamas.marton on 09/03/2017.
 */

public interface HomeInteractor {

    void startContentRequest(ContentType contentType);

    void startSearchRequest(ContentType contentType, String query);

    void setHomeInteractorListener(HomeInteractorImpl.HomeInteractorListener homeInteractorListener);
}