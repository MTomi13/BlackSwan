package hu.marton.tamas.blackswan;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class SuggestionProvider extends SearchRecentSuggestionsProvider{

    public final static String AUTHORITY = "hu.marton.tamas.blackswan.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
