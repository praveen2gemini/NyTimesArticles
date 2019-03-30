package com.nytimes.articles.dashboard.presenter;

import com.nytimes.apibase.rx.SimpleObserver;
import com.nytimes.apibase.services.models.ViewedArticles;
import com.nytimes.articles.R;
import com.nytimes.articles.dashboard.widget.ArticleType;
import com.nytimes.apibase.ApiBase;
import com.nytimes.apibase.BuildConfig;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Praveen on 29/03/19.
 */
public class DashboardPresenterImpl implements DashboardPresenter {

    private ViewUpdater viewUpdater;

    public DashboardPresenterImpl(@NonNull ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    @Override
    public void fetchPopularViewedArticles(@NonNull ArticleType type) {
        if (viewUpdater.isDeviceOffline()) {
            viewUpdater.showEmptyMessage();
            viewUpdater.showErrorMessage(R.string.http_network_error, R.string.http_no_network);
            return;
        }
        viewUpdater.showProgress();
        ApiBase.getInstance().getPopularArticleService()
                .fetchViewedPopularArticles(type.getPeriod(), BuildConfig.NY_APP_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<ViewedArticles>() {
                    @Override
                    public void onNext(ViewedArticles viewedArticles) {
                        super.onNext(viewedArticles);
                        viewUpdater.hideProgress();
                        if (null == viewedArticles.getResults() || viewedArticles.getResults().isEmpty()) {
                            viewUpdater.showEmptyMessage();
                        } else {
                            viewUpdater.updateViewedArticles(viewedArticles.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        viewUpdater.hideProgress();
                        viewUpdater.showErrorMessage(R.string.http_network_error, R.string.https_something_went_wrong);
                    }
                });
    }
}
