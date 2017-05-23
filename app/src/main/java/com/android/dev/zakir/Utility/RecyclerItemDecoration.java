package com.android.dev.zakir.Utility;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zakir.pervez on 22/5/17.
 */


/**
 * This class is use to set decor item or divider between RecyclerView Cell's
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable drawable;

    public RecyclerItemDecoration(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + drawable.getIntrinsicHeight();
            drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            drawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.top = drawable.getIntrinsicHeight();
    }
}
