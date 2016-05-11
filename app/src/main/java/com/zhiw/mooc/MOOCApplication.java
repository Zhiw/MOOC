package com.zhiw.mooc;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * ClassName: MOOCApplication
 * Desc:
 * Created by zhiw on 16/4/1.
 */
public class MOOCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        // 初始化BmobSDK
        Bmob.initialize(this, getString(R.string.bmob_app_id));
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务
        BmobPush.startWork(this);
    }
}
