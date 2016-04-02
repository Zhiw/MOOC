package com.zhiw.mooc.framework.base;

import android.app.Activity;
import android.view.View;

/**
 * ClassName: BasePresenter
 * Desc: TODO
 * Created by zhiw on 16/3/20.
 */
public class BasePresenter<T extends BaseView> {

    public Activity activity;

    public T viewImpl;

    public BasePresenter(Activity activity, T viewImpl) {
        this.activity = activity;
        this.viewImpl = viewImpl;
    }

    public void init(){
        this.init(null);

    }

    public void init(View view){
        viewImpl.initView(view);
        viewImpl.initListener();

    }

}
