package com.nytimes.articles.core;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.nytimes.apibase.ApiBase;

/**
 * @author Praveen on 29/03/19.
 */
public class NYTimeApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApiBase.with(this, true);
    }
}
