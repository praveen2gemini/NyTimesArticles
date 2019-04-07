package com.nytimes.articles.test_utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nytimes.articles.dashboard.widget.SingleArticleView;

/**
 * @author Praveen
 *
 * This class used to customise the {@link ViewAssertion} to check the {@link View} with the condition.
 *
 * @param <A> - {@link com.nytimes.apibase.services.models.Results} which instance we are using for building the data
 */
public class RecyclerItemViewAssertion<A> implements ViewAssertion {

    private RecyclerViewInteraction.SingleItemViewAssertion<A> singleItemViewAssertion;
    private A item;
    private int position;

    public RecyclerItemViewAssertion(int position, A item, RecyclerViewInteraction.SingleItemViewAssertion<A> singleItemViewAssertion) {
        this.position = position;
        this.item = item;
        this.singleItemViewAssertion = singleItemViewAssertion;
    }

    @Override
    public final void check(View view, NoMatchingViewException e) {
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.ViewHolder viewHolderForPosition = recyclerView.findViewHolderForLayoutPosition(position);
        if (viewHolderForPosition == null) {
            throw (new PerformException.Builder())
                    .withActionDescription(toString())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new IllegalStateException("No view holder is available at the position of : " + position))
                    .build();
        } else {
            if (viewHolderForPosition.itemView instanceof SingleArticleView) {
                SingleArticleView viewAtPosition = (SingleArticleView) viewHolderForPosition.itemView;
                singleItemViewAssertion.check(item, viewAtPosition, e);
            }
        }
    }
}