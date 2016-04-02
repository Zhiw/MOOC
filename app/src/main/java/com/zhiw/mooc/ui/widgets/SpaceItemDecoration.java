package com.zhiw.mooc.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ClassName: SpaceItemDecoration
 * Desc:
 * Created by zhiw on 16/3/31.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildPosition(view)!=0){
            outRect.top = space;
        }
    }
}
