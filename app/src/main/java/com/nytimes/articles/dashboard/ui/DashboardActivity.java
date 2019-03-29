package com.nytimes.articles.dashboard.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.nytimes.articles.R;
import com.nytimes.articles.core.BaseActivity;
import com.nytimes.articles.dashboard.widget.ArticleType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private SearchView searchView;

    public static Intent getIntent(Context context) {
        return new Intent(context, DashboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        configureToolbar(false);
        initDrawer();
        if (null == savedInstanceState) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_frame, DashboardFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    protected void configureToolbar(boolean canGoBack) {
        super.configureToolbar(canGoBack);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        setScreenTitle(R.string.title_home);
    }


    /**
     * It initiates the navigation drawer state and listeners.
     */
    private void initDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.nav_open, R.string.nav_close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                applyFilter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                applyFilter(query);
                return false;
            }
        });
        return true;
    }


    /**
     * This method used apply user query on list of populated items. It would filter the user's query
     *
     * @param query - user's input for filter
     */
    private void applyFilter(String query) {
        Fragment dashboardFragment = getSupportFragmentManager().findFragmentById(R.id.container_frame);
        if (dashboardFragment instanceof DashboardFragment) {
            ((DashboardFragment) dashboardFragment).applyFilter(query);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean isOptionsItemSelected;

        switch (item.getItemId()) {
            case android.R.id.home:
                isOptionsItemSelected = true;
                if (!isDrawerClosed()) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.action_article_one:
                isOptionsItemSelected = true;
                showArticle(ArticleType.ARTICLE_1);
                break;

            case R.id.action_article_seven:
                isOptionsItemSelected = true;
                showArticle(ArticleType.ARTICLE_7);
                break;


            case R.id.action_article_thirty:
                isOptionsItemSelected = true;
                showArticle(ArticleType.ARTICLE_30);
                break;

            case R.id.action_search:
                isOptionsItemSelected = true;
                Snackbar.make(mDrawerLayout, R.string.text_under_development, Snackbar.LENGTH_SHORT).show();
                break;
            default:
                isOptionsItemSelected = super.onOptionsItemSelected(item);
                break;
        }
        return isOptionsItemSelected;
    }

    /**
     * This method would populate the seleted populated article list {@link android.support.v7.widget.RecyclerView}
     *
     * @param type - selected {@link ArticleType}
     */
    private void showArticle(@NonNull ArticleType type) {
        Fragment dashboardFragment = getSupportFragmentManager().findFragmentById(R.id.container_frame);
        if (dashboardFragment instanceof DashboardFragment) {
            ((DashboardFragment) dashboardFragment).loadArticles(type);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        isDrawerClosed(); // close drawer when item is tapped
        if (menuItem.getItemId() == R.id.nav_about) {
            Snackbar.make(mDrawerLayout, R.string.text_under_development, Snackbar.LENGTH_SHORT).show();
        } else if (menuItem.getItemId() == R.id.nav_help) {
            Snackbar.make(mDrawerLayout, R.string.text_under_development, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(mDrawerLayout, R.string.text_under_development, Snackbar.LENGTH_SHORT).show();
        }
        // Add code here to update the UI based on the item selected
        // For example, swap UI fragments here

        return true;
    }

    /**
     * It wouls check the current state of navigation drawer, If opened, It would closed it.
     *
     * @return true whether drawer is closed or false.
     */
    private boolean isDrawerClosed() {
        boolean isClosed = false;
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            isClosed = true;
        }
        return isClosed;
    }

    @Override
    public void onBackPressed() {
        if (null != searchView
                && !searchView.isIconified()) {
            searchView.setIconified(true);
        } else if (!isDrawerClosed()) {
            super.onBackPressed();
        }
    }
}

