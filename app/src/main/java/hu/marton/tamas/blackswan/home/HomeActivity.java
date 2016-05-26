package hu.marton.tamas.blackswan.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import javax.inject.Inject;

import hu.marton.tamas.blackswan.BlackSwanActivity;
import hu.marton.tamas.blackswan.R;
import hu.marton.tamas.blackswan.SuggestionProvider;
import hu.marton.tamas.blackswan.api.Popular.model.ContentType;
import hu.marton.tamas.blackswan.api.Popular.model.ResponseContent;
import hu.marton.tamas.blackswan.util.ViewHelper;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by tamas.marton on 26/05/2016.
 */
public class HomeActivity extends BlackSwanActivity implements HomeActivityController.ContentRequestListener {

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
        setupRecyclerView();

        setupBottomBar(savedInstanceState);

        bottomBarClicked(ContentType.MOVIES);
        homeActivityController.setContentRequestListener(this);
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

    private void setupBottomBar(Bundle savedInstanceState) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cordinator_layout);
        if (coordinatorLayout != null) {
            bottomBar = BottomBar.attachShy(coordinatorLayout, findViewById(R.id.recyclerview), savedInstanceState);
        }
        bottomBar.setItemsFromMenu(R.menu.bottom_bar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
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

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

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

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }

    @Override
    public void contentRequestSuccess(ResponseContent responseContent) {
        setupRecycleViewAdapter(responseContent);
        ViewHelper.setVisibility(View.GONE, findViewById(R.id.home_progress_bar));
    }

    @Override
    public void contentRequestFailed(Throwable throwable) {

    }

    /**
     * @param responseContent setup the SearchResultAdapter
     */
    private void setupRecycleViewAdapter(ResponseContent responseContent) {
        HomeAdapter adapter = new HomeAdapter(responseContent, ViewHelper.getImageOptions());
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setDuration(ANIM_DURATION);
        recyclerView.setAdapter(scaleAdapter);
    }
}
