package com.zhiw.mooc.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * ClassName: SystemUtils
 * Desc:
 * Created by zhiw on 16/5/26.
 */
public class SystemUtils {

    /**
     * return deviceId
     * @param context Context
     * @return deviceId
     */
    public static String getDeviceId(Context context){
        TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
