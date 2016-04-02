package com.zhiw.mooc.ui.IView;

import com.zhiw.mooc.framework.base.BaseView;

/**
 * ClassName: ILoginView
 * Desc:
 * Created by zhiw on 16/3/30.
 */
public interface ILoginView extends BaseView{

    String getUserName();

    String getPassword();

    void loginSuccess();

    void registerSuccess();

    void failed();

}
