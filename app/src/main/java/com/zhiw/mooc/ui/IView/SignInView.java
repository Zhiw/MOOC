package com.zhiw.mooc.ui.IView;

import com.zhiw.mooc.framework.base.BaseView;

/**
 * ClassName: SignInView
 * Desc:
 * Created by zhiw on 16/4/26.
 */
public interface SignInView extends BaseView {



    double getLatitude();

    double getLongitude();

    String getAddress();

    String getDescription();

    String getCode();

}
