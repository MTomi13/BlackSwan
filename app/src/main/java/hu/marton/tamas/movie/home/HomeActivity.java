package hu.marton.tamas.movie.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import javax.inject.Inject;

import hu.marton.tamas.movie.MovieActivity;
import hu.marton.tamas.movie.R;
import hu.marton.tamas.movie.api.Popular.model.ContentType;
import hu.marton.tamas.movie.api.Popular.model.ResponseContent;
import hu.marton.tamas.movie.api.Popular.model.ResultWrapper;
import hu.marton.tamas.movie.details.DetailsActivity;
import hu.marton.tamas.movie.util.GeneralErrorHandler;
import hu.marton.tamas.movie.util.SuggestionProvider;
import hu.marton.tamas.movie.util.ViewHelper;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import me.zhanghai.android.materialprogressbar.IndeterminateHorizontalProgressDrawable;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class HomeActivity extends MovieActivity implements HomeActivityController.ContentRequestListener, HomeAdapter.ResultWrapperClickListener {

    private static final int ANIM_DURATION = 800;

    @Inject
    HomeActivityController homeActivityController;

    private BottomBar bottomBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        setupBottomBar();
        handleIntent(getIntent());
        setupRecyclerView();

        bottomBarClicked(ContentType.MOVIES);
        homeActivityController.setContentRequestListener(this);
        ((ProgressBar) findViewById(R.id.home_progress_bar)).setIndeterminateDrawable(new IndeterminateHorizontalProgressDrawable(this));
    }

    /**
     * setup toolbar
     */
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * setup recycleView
     */
    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    /**
     */
    private void setupBottomBar() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setItems(R.xml.bottom_bar_menu);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.bottomBarItemOne:
                        bottomBarClicked(ContentType.MOVIES);
                        break;
                    case R.id.bottomBarItemTwo:
                        bottomBarClicked(ContentType.SERIES);
                        break;
                    default:
                        bottomBarClicked(ContentType.PEOPLE);
                }
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    /**
     * @param contentType contentType
     *                    handle bottom bar item click
     */
    private void bottomBarClicked(ContentType contentType) {
        homeActivityController.startContentRequest(contentType);
        ViewHelper.setVisibility(View.VISIBLE, findViewById(R.id.home_progress_bar));
    }

    /**
     * @param menu
     * @return Inflate menu to add items to action bar if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        setupSearchView(menu);
        return true;
    }

    /**
     * @param menu Associate searchable.xml configuration with the SearchView
     */
    private void setupSearchView(final Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryRefinementEnabled(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    /**
     * @param intent handle search intent
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            startQueryByContentType(query);
        }
    }

    /**
     * @param query query
     *              start search by content type
     */
    private void startQueryByContentType(String query) {
        switch (bottomBar.getCurrentTabPosition()) {
            case 0:
                homeActivityController.startSearchRequest(ContentType.MOVIES, query);
                break;
            case 1:
                homeActivityController.startSearchRequest(ContentType.SERIES, query);
                break;
            default:
                homeActivityController.startSearchRequest(ContentType.PEOPLE, query);
        }
    }

    @Override
    public void contentRequestSuccess(ResponseContent responseContent) {
        setupRecycleViewAdapter(responseContent);
        ViewHelper.setVisibility(View.GONE, findViewById(R.id.home_progress_bar));
    }

    @Override
    public void contentRequestFailed(Throwable throwable) {
        GeneralErrorHandler.showErrorMessage(this, getString(R.string.snackbar_text));
    }

    /**
     * @param responseContent setup the SearchResultAdapter
     */
    private void setupRecycleViewAdapter(ResponseContent responseContent) {
        HomeAdapter adapter = new HomeAdapter(responseContent, ViewHelper.getImageOptions(), this);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setDuration(ANIM_DURATION);
        recyclerView.setAdapter(scaleAdapter);
    }

    @Override
    public void resultWrapperClicked(ResultWrapper resultWrapper, ContentType contentType) {
        Intent intent = new Intent(this, DetailsActivity.class);
        resultWrapper.setContentType(contentType);
        intent.putExtra(ResultWrapper.class.getName(), resultWrapper);
        startActivity(intent);
    }
}
