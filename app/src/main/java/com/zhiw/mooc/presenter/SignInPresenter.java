package com.zhiw.mooc.presenter;

import android.app.Activity;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.ui.IView.ISignInView;

/**
 * ClassName: SignInPresenter
 * Desc:
 * Created by zhiw on 16/4/26.
 */
public class SignInPresenter extends BasePresenter<ISignInView> {

    public SignInPresenter(Activity activity, ISignInView viewImpl) {
        super(activity, viewImpl);
    }
}
