package com.qzsang.retrofittest.util;

import android.util.Log;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class LogUtil {


    public static void e(String tag, String str) {
        if (ConfigUtil.isDebug) {
            Log.e(tag, str + "");
        }
    }

}
