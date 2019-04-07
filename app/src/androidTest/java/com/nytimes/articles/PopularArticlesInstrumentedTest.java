package com.nytimes.articles;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nytimes.apibase.services.models.Results;
import com.nytimes.articles.core.ViewTypeAdapter;
import com.nytimes.articles.dashboard.ui.DashboardActivity;
import com.nytimes.articles.dashboard.widget.SingleArticleView;
import com.nytimes.articles.test_utils.RecyclerItemViewAssertion;
import com.nytimes.articles.test_utils.RecyclerViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

/**
 * @author Praveen
 * <p>
 * Instrumented test, which will execute on an Android device.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PopularArticlesInstrumentedTest {

    @Rule
    public ActivityTestRule<DashboardActivity> menuActivityTestRule =
            new ActivityTestRule<>(DashboardActivity.class, true, true);
    private Context appContext;

    @Mock
    private Results expectedResults;
    @Mock
    private List<Results> expectedAllResults;



    @Before
    public void init() {
        appContext = InstrumentationRegistry.getTargetContext();
        MockitoAnnotations.initMocks(this);
        when(expectedResults.getTitle()).thenReturn("A Mysterious Infection, Spanning the Globe in a Climate of Secrecy"); // By Default
    }

    @Test
    public void testAndCheckItemTitle() {
        onView(withId(R.id.recycler_article_view))
                .check(matches(hasDescendant(withText(expectedResults.getTitle()))));

    }

    @Test
    public void testCustomRecyclerItemViewAssertion() {
        when(expectedResults.getTitle()).thenReturn("How Rupert Murdoch’s Empire of Influence Remade the World");
        RecyclerViewInteraction.<Results>onRecyclerView(withId(R.id.recycler_article_view))
                .withItems(expectedAllResults)
                .check(new RecyclerViewInteraction.SingleItemViewAssertion<Results>() {
                    @Override
                    public void check(Results expectedResults, SingleArticleView view, NoMatchingViewException e) {
                        matches(hasDescendant(withText(expectedResults.getTitle()))).check(view, e);
                    }
                });
    }

    @Test
    public void testRecyclerCheckTitleMatcher() {
        int expectedPosition = 6;
        when(expectedResults.getTitle()).thenReturn("How Rupert Murdoch’s Empire of Influence Remade the World");

        onView(withId(R.id.recycler_article_view))
                .perform(scrollToPosition(expectedPosition))
                .check(new RecyclerItemViewAssertion<>(expectedPosition, expectedResults, new RecyclerViewInteraction.SingleItemViewAssertion<Results>() {
                    @Override
                    public void check(Results expectedResults, SingleArticleView view, NoMatchingViewException e) {
                        matches(withTitleViewMatcher(expectedResults.getTitle()))
                                .check(view, e);
                    }
                }));
    }

    /**
     * It test just scroll to 5th position and performs click action on {@link android.support.v7.widget.RecyclerView}
     */
    @Test
    public void testScrollAndPerformClickAction() {
        int i = 5; // index 5th position
        onView(withId(R.id.recycler_article_view))
                .perform(scrollToPosition(i)).perform(click());
    }

    /**
     * It test the item by subtitle in {@link com.nytimes.articles.core.ViewTypeAdapter.ViewHolder}
     */
    @Test
    public void testWithSubTitleAndDetectItem() {
        when(expectedResults.getTitle()).thenReturn("By MATT RICHTEL and ANDREW JACOBS");
        onView(withId(R.id.recycler_article_view)).perform(
                RecyclerViewActions.scrollToHolder(
                        withRecyclerHolderMatcher(expectedResults.getTitle())
                )
        );
    }


    /**
     * This method identifies the test content in selected {@link SingleArticleView} and find the tile or sub title holding the testing content or not.
     *
     * @param text - query text
     */
    public static Matcher<View> withTitleViewMatcher(String text) {
        return new BoundedMatcher<View, SingleArticleView>(SingleArticleView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(SingleArticleView itemView) {
                return isViewMatchedWithText(itemView, text);
            }
        };
    }

    /**
     * This method used to match the content from {@link com.nytimes.articles.core.ViewTypeAdapter.ViewHolder}
     *
     * @param text - given text to match with view
     */
    public static Matcher<ViewTypeAdapter.ViewHolder> withRecyclerHolderMatcher(final String text) {
        return new BoundedMatcher<ViewTypeAdapter.ViewHolder, ViewTypeAdapter.ViewHolder>(ViewTypeAdapter.ViewHolder.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(ViewTypeAdapter.ViewHolder item) {
                return isViewMatchedWithText((SingleArticleView) item.itemView, text);
            }
        };
    }

    private static boolean isViewMatchedWithText(SingleArticleView itemView, String text) {
        TextView titleViewText = itemView.findViewById(R.id.article_title);
        TextView subTitleViewText = itemView.findViewById(R.id.article_sub_title);
        if (titleViewText == null) {
            return false;
        }
        if (subTitleViewText == null) {
            return false;
        }
        return titleViewText.getText().toString().contains(text)
                || subTitleViewText.getText().toString().contains(text);
    }

    @Test
    public void checkSearchFromMenu() {

        // Context of the app under test.
        onView(withId(R.id.action_search)).perform(click());

        //Search new query
        onView(isAssignableFrom(EditText.class)).perform(typeText("Plastic"), pressImeActionButton());

        //Clear entered previous values and re-search new query
        onView(isAssignableFrom(EditText.class)).perform(clearText(), typeText("trump"), pressImeActionButton());

        //check the selected recycler item
        onView(withId(R.id.recycler_article_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }

    @Test
    public void checkArticleOneFromMenu() {
        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText(R.string.text_menu_article_1)).perform(click());
    }


    @Test
    public void checkArticleSevenFromMenu() {
        // Context of the app under test.
        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText(R.string.text_menu_article_7)).perform(click());
    }

    @Test
    public void checkArticleThirtyFromMenu() {
        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText(R.string.text_menu_article_30)).perform(click());
    }
}
