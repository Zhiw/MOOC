package com.zhiw.mooc.framework.base;

import android.view.View;

/**
 * ClassName: BaseView
 * Desc:
 * Created by zhiw on 16/3/27.
 */
public interface BaseView {

    void initView(View view);

    void initListener();

    void showLoading(boolean show);



}
