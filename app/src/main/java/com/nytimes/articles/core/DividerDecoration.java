package com.nytimes.articles.core;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.nytimes.articles.R;

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private int scaledSize;
    private Paint dividerPaint;

    /**
     * Creates a custom DividerDecoration instance.
     *
     * @param context
     * @param color
     * @param dividerHeight
     * @param unit
     */
    public DividerDecoration(@NonNull Context context,
                             @ColorInt int color,
                             int dividerHeight,
                             int unit) {
        if (dividerHeight > 0) {
            Resources res = context.getResources();
            scaledSize = (int) TypedValue.applyDimension(unit,
                    dividerHeight,
                    res.getDisplayMetrics());
            dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            dividerPaint.setColor(color);
            dividerPaint.setStyle(Paint.Style.STROKE);
            dividerPaint.setStrokeWidth(scaledSize);
        }
    }

    /**
     * Returns the default DividerDecoration.
     *
     * @param context
     * @return
     */
    public static DividerDecoration getDefault(@NonNull Context context) {
        return new DividerDecoration(context,
                ContextCompat.getColor(context, R.color.default_divider),
                1,
                TypedValue.COMPLEX_UNIT_DIP);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        if (scaledSize > 0) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            View child = null;
            int y = 0;

            // Draw at the bottom of each child
            for (int i = 0; i < parent.getChildCount(); i++) {
                child = parent.getChildAt(i);
                if (shouldDrawDividerOverChild(child)) {
                    y = child.getTop() + child.getHeight();
                    int dividerMarginLeft = getDividerMarginLeft(child);
                    c.drawLine(dividerMarginLeft > 0 ? dividerMarginLeft : left, y, right, y, dividerPaint);
                }
            }
        }
    }

    /**
     * Called before drawing divider over a child. Return {@code true} to draw divider
     * over the given view, else return {@code false}.
     *
     * @param child
     * @return
     */
    protected boolean shouldDrawDividerOverChild(View child) {
        return true;
    }

    /**
     * Called before drawing divider over a child. Return {@code true} to draw divider
     * over the given view, else return {@code false}.
     *
     * @param child
     * @return
     */
    protected int getDividerMarginLeft(View child) {
        return 0;
    }
}
