package com.yozoe.common;

import android.util.Log;

/**
 * Created by wangdong on 16/7/24.
 */
public class L {

    private static String mTag = "hehe";

    public static void d(String text) {
        Log.d(mTag, text);
    }

    public static void d(String tag, String text) {
        Log.d(tag, text);
    }

}
