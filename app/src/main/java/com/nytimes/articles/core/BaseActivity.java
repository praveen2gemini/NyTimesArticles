package com.nytimes.articles.core;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.nytimes.articles.R;

/**
 * @author Praveen on 29/03/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    /**
     * Default implementation.
     */
    @CallSuper
    protected void configureToolbar(boolean canGoBack) {
        // Sets support ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(canGoBack);
        }
    }

    @CallSuper
    protected void setScreenTitle(@StringRes int titleResId) {
        String title = getString(titleResId);
        setScreenTitle(title);
    }

    @CallSuper
    protected void setScreenTitle(@Nullable String screenTitle) {
        try {
            ActionBar actionBar = getSupportActionBar();
            if (null != actionBar && !TextUtils.isEmpty(screenTitle)) {
                actionBar.setTitle(screenTitle);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception on setScreenTitle()", e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean isOptionsItemSelected;
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                isOptionsItemSelected = true;
                onBackPressed();
                break;
            default:
                isOptionsItemSelected = super.onOptionsItemSelected(item);
                break;
        }
        return isOptionsItemSelected;
    }

    @Override
    public void onBackPressed() {
        // Let the fragment handle "back"
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container_frame);
        boolean isBackHandled = (fragment instanceof IBaseFragment) &&
                ((IBaseFragment) fragment).onBackPressed();
        if (!isBackHandled) {
            finish();
        }
    }

}
