package com.nytimes.apibase;

import android.content.Context;
import android.util.Log;

import com.nytimes.apibase.di.ApiBaseModule;
import com.nytimes.apibase.di.DaggerApiBaseComponent;
import com.nytimes.apibase.exceptions.RetrofitException;
import com.nytimes.apibase.services.PopularArticleService;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import okhttp3.logging.HttpLoggingInterceptor;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * @author Praveen on 23/03/19.
 */
public class ApiBase {

    public static final SimpleDateFormat INDIAN_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());//2019-01-04 00:40:46
    /**
     * Log Tag
     */
    private static final String TAG = ApiBase.class.getSimpleName();
    @Inject
    PopularArticleService popularArticleService;

    private Context context;
    private HttpLoggingInterceptor.Level loggerLevel = HttpLoggingInterceptor.Level.NONE;

    public static void with(Context context, boolean isDebugEnabled) {
        ApiBase api = getInstance();
        if (context == null) {
            throw new ExceptionInInitializerError("Context cannot be null");
        }
        if (api.context != null) {
            throw new ExceptionInInitializerError("Already initialized - please invoke from Application.onCreate");
        }
        api.context = context;
        api.loggerLevel = isDebugEnabled ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        api.init();
    }

    public static ApiBase getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void init() {
        DaggerApiBaseComponent
                .builder()
                .apiBaseModule(new ApiBaseModule(context))
                .build()
                .inject(this);

        //Todo: yet to check again to catch unhandled errors. It will receive all errors here.
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                super.handleError(e);
                if (null != e && !(e instanceof RetrofitException) && (null != e.getCause())) {
                    Log.e(TAG, "RxJava2 Unhandled Exception: " + e.getCause().toString(), e);
                }
            }
        });

    }

    public PopularArticleService getPopularArticleService() {
        return popularArticleService;
    }

    public HttpLoggingInterceptor.Level getRetrofitLogLevel() {
        return loggerLevel;
    }

    private static final class InstanceHolder {
        private static final ApiBase INSTANCE = new ApiBase();
    }
}
