package com.zhiw.mooc.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import io.vov.vitamio.widget.MediaController;

/**
 * ClassName: MyMediaController
 * Desc:
 * Created by zhiw on 16/4/8.
 */
public class MyMediaController extends MediaController {

    private Context mContext;
    public MyMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMediaController(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected View makeControllerView() {
        return ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("layout_media_controller", "layout", mContext.getPackageName()), this);

    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
    }
}
