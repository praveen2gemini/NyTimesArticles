package com.nytimes.articles;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.nytimes.articles.dashboard.ui.DashboardActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Praveen
 *
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

    @Before
    public void init() {
        appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void checkSearchFromMenu() {
        try {
            Thread.sleep(3000);
            // Context of the app under test.
            onView(withId(R.id.action_search)).perform(click());

            //Search new query
            onView(isAssignableFrom(EditText.class)).perform(typeText("Plastic"), pressImeActionButton());

            //Clear entered previous values and re-search new query
            onView(isAssignableFrom(EditText.class)).perform(clearText(), typeText("trump"), pressImeActionButton());

            //check the selected recycler item
            onView(withId(R.id.recycler_article_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkArticleOneFromMenu() {
        try {
            Thread.sleep(3000);
            // Context of the app under test.
            openActionBarOverflowOrOptionsMenu(appContext);
            onView(withText(R.string.text_menu_article_1)).perform(click());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void checkArticleSevenFromMenu() {
        try {
            Thread.sleep(3000);
            // Context of the app under test.
            openActionBarOverflowOrOptionsMenu(appContext);
            onView(withText(R.string.text_menu_article_7)).perform(click());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkArticleThirtyFromMenu() {
        try {
            Thread.sleep(3000);
            // Context of the app under test.
            openActionBarOverflowOrOptionsMenu(appContext);
            onView(withText(R.string.text_menu_article_30)).perform(click());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
