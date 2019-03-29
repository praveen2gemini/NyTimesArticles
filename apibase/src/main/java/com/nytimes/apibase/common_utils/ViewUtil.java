package com.nytimes.apibase.common_utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Basic UI/View utility methods.
 *
 * @author Praveen
 */
public class ViewUtil {

    /**
     * NOTE:
     * To avoid creating an array, if there is only one input parameter.
     *
     * @param views
     */
    public static void show(View... views) {
        for (View aView : views) {
            if (null == aView) continue;
            aView.setVisibility(View.VISIBLE);

        }
    }

    public static void hide(View... views) {
        for (View aView : views) {
            if (null == aView) continue;
            aView.setVisibility(View.GONE);
        }
    }


    public static void enable(final boolean status,
                              final View... views) {
        for (View aView : views) {
            if (null == aView) continue;
            aView.setEnabled(status);
        }
    }

    public static void hideKeyboard(Activity activity) {
        View focusedView = (null != activity) ? activity.getCurrentFocus() : null;
        if (null != focusedView) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (null == imm) return;
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    // Code snippet for show the keyboard
    public static void showKeyboard(Activity activity) {
        View v = activity.getWindow().getCurrentFocus();
        if (null != v) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null == inputManager) return;
            inputManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}