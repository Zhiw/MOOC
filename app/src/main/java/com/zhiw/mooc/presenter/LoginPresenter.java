package com.zhiw.mooc.presenter;

import android.app.Activity;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.ui.IView.ILoginView;
import com.zhiw.mooc.utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * ClassName: LoginPresenter
 * Desc:
 * Created by zhiw on 16/3/30.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(Activity activity, ILoginView viewImpl) {
        super(activity, viewImpl);
    }

    public void login(){
        BmobUser user = new BmobUser();
        user.setUsername(viewImpl.getUserName());
        user.setPassword(viewImpl.getPassword());
        user.login(activity, new SaveListener() {
            @Override
            public void onSuccess() {
                viewImpl.loginSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.get().showShortToast(activity,s);
                viewImpl.failed();

            }
        });

    }

    public void register(){
        BmobUser user = new BmobUser();
        user.setUsername(viewImpl.getUserName());
        user.setPassword(viewImpl.getPassword());
        user.signUp(activity, new SaveListener() {
            @Override
            public void onSuccess() {
                viewImpl.registerSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.get().showShortToast(activity,s);
                viewImpl.failed();

            }
        });
    }


}
