package com.zhiw.mooc;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

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
    }
}
