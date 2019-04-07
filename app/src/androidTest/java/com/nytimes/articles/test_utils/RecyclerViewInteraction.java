package com.nytimes.articles.test_utils;

import android.support.test.espresso.NoMatchingViewException;
import android.view.View;

import com.nytimes.articles.dashboard.widget.SingleArticleView;

import org.hamcrest.Matcher;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;

public class RecyclerViewInteraction<W> {

    private Matcher<View> viewMatcher;
    private List<W> items;

    private RecyclerViewInteraction(Matcher<View> viewMatcher) {
        this.viewMatcher = viewMatcher;
    }

    public static <W> RecyclerViewInteraction<W> onRecyclerView(Matcher<View> viewMatcher) {
        return new RecyclerViewInteraction<>(viewMatcher);
    }

    public RecyclerViewInteraction<W> withItems(List<W> items) {
        this.items = items;
        return this;
    }

    public RecyclerViewInteraction<W> check(SingleItemViewAssertion<W> singleItemViewAssertion) {
        for (int i = 0; i < items.size(); i++) {
            onView(viewMatcher)
                    .perform(scrollToPosition(i))
                    .check(new RecyclerItemViewAssertion<>(i, items.get(i), singleItemViewAssertion));
        }
        return this;
    }

    public interface SingleItemViewAssertion<W> {
        void check(W item, SingleArticleView view, NoMatchingViewException e);
    }
}