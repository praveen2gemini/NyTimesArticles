package com.nytimes.articles.dashboard.presenter;

import android.support.annotation.StringRes;

import com.nytimes.apibase.services.models.Results;
import com.nytimes.articles.dashboard.widget.ArticleType;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

/**
 * @author Praveen on 29/03/19.
 */
public interface DashboardPresenter {

    void fetchPopularViewedArticles(@NonNull ArticleType articleType);

    interface ViewUpdater {

        boolean isDeviceOffline();

        void showProgress();

        void hideProgress();

        void showEmptyMessage();

        void showErrorMessage(@StringRes int errorTitle, @StringRes int errorMessage);

        void updateViewedArticles(ArrayList<Results> results);
    }
}
