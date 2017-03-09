package hu.marton.tamas.movie.home;

import android.content.Intent;
import android.provider.SearchRecentSuggestions;

import hu.marton.tamas.movie.api.Popular.model.ContentType;

/**
 * Created by tamas.marton on 09/03/2017.
 */

public interface HomePresenter {

    void startFetchContent(ContentType contentType);

    void handleSearchIntent(Intent intent, SearchRecentSuggestions searchRecentSuggestions, ContentType contentType);
}