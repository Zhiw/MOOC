package com.zhiw.mooc.presenter;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.model.Attendance;
import com.zhiw.mooc.ui.IView.SignInView;
import com.zhiw.mooc.utils.SystemUtils;

import android.app.Activity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * ClassName: SignInPresenter
 * Desc:
 * Created by zhiw on 16/4/26.
 */
public class SignInPresenter extends BasePresenter<SignInView> {

    public SignInPresenter(Activity activity, SignInView viewImpl) {
        super(activity, viewImpl);
    }

    public void signIn() {

        BmobUser user = BmobUser.getCurrentUser(activity);
        double latitude = viewImpl.getLatitude();
        double longitude = viewImpl.getLongitude();
        String address = viewImpl.getAddress();
        String description = viewImpl.getDescription();
        String device = SystemUtils.getDeviceId(activity);
        String code = viewImpl.getCode();
        Attendance attendance = new Attendance(user, latitude, longitude, address, description, device, code);

        attendance.save(activity, new SaveListener() {
            @Override
            public void onSuccess() {
                viewImpl.SignInSuccess();

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }
}
