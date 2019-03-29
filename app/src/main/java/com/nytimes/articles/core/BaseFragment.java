package com.nytimes.articles.core;

import android.support.v4.app.Fragment;

/**
 * @author Praveen on 29/03/19.
 */
public class BaseFragment extends Fragment implements IBaseFragment{

    protected final String LOG_TAG_NAME = getClass().getSimpleName();

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public boolean onExit() {
        return false;
    }
}
