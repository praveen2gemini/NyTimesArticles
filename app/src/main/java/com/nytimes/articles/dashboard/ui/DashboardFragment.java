package com.nytimes.articles.dashboard.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nytimes.apibase.common_utils.DeviceUtil;
import com.nytimes.apibase.services.models.Results;
import com.nytimes.articles.R;
import com.nytimes.articles.core.BaseFragment;
import com.nytimes.articles.core.DividerDecoration;
import com.nytimes.articles.dashboard.adapter.PopularArticleAdapter;
import com.nytimes.articles.dashboard.presenter.DashboardPresenter;
import com.nytimes.articles.dashboard.presenter.DashboardPresenterImpl;
import com.nytimes.articles.dashboard.widget.ArticleType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nytimes.apibase.common_utils.ViewUtil.hide;
import static com.nytimes.apibase.common_utils.ViewUtil.show;

/**
 * @author Praveen on 29/03/19.
 */
public class DashboardFragment extends BaseFragment implements DashboardPresenter.ViewUpdater {


    @BindView(R.id.recycler_article_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty_status)
    TextView emptyStatusView;

    @BindView(R.id.progressive_view)
    View progressiveView;

    private DashboardPresenter dashboardPresenter;

    private PopularArticleAdapter articleAdapter;

    public static Fragment newInstance() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        dashboardFragment.setRetainInstance(true);
        return dashboardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardPresenter = new DashboardPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(DividerDecoration.getDefault(view.getContext()));
        articleAdapter = new PopularArticleAdapter(getContext()) {
            @Override
            public void onArticleSelected(int position, Results selectedResult) {
                Snackbar.make(mRecyclerView, "" + selectedResult.getTitle(), Snackbar.LENGTH_SHORT).show();
            }
        };
        mRecyclerView.setAdapter(articleAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dashboardPresenter.fetchPopularViewedArticles(ArticleType.ARTICLE_1);
    }

    @Override
    public boolean isDeviceOffline() {
        return DeviceUtil.isOffline(getContext());
    }

    @Override
    public void showProgress() {
        if (!isResumed() || isRemoving()) return;
        show(progressiveView);
        hide(mRecyclerView, emptyStatusView);
    }

    @Override
    public void hideProgress() {
        if (!isResumed() || isRemoving()) return;
        hide(progressiveView, emptyStatusView);
        show(mRecyclerView);
    }

    @Override
    public void showEmptyMessage() {
        if (!isResumed() || isRemoving()) return;
        hide(progressiveView, mRecyclerView);
        show(emptyStatusView);
    }

    @Override
    public void showErrorMessage(@StringRes int errorTitle, @StringRes int errorMessage) {
        if (null == getContext() || !isResumed() || isRemoving()) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (errorTitle != -1) {
            builder.setTitle(errorTitle);
        }
        if (errorMessage != -1) {
            builder.setMessage(errorMessage);
        }
        builder.setPositiveButton(android.R.string.ok, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void updateViewedArticles(ArrayList<Results> results) {
        if (!isResumed() || isRemoving()) return;
        articleAdapter.upadtePopularArticles(results);
    }

    public void loadArticles(@NonNull ArticleType type) {
        dashboardPresenter.fetchPopularViewedArticles(type);
    }

    public void applyFilter(@NonNull String query) {
        articleAdapter.getFilter().filter(query);
    }
}
