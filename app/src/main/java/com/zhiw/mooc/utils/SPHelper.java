package com.zhiw.mooc.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ClassName: SPHelper
 * Desc:
 * Created by zhiw on 16/4/1.
 */
public class SPHelper {
    private static final String SP_NAME = "app_data";

    private static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     *
     * @param context
     * @param is true:设置 false:默认主题
     */
    public static void setTheme(Context context,boolean is){
        SharedPreferences sharedPreferences = getSP(context);
        sharedPreferences.edit().putBoolean("theme",is).apply();
    }

    public static boolean getTheme(Context context) {
        SharedPreferences sharedPreferences = getSP(context);
        return sharedPreferences.getBoolean("theme", false);
    }

}
