package com.example.mobilesafe.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wangdong on 16/8/1.
 */
public class ToastUtil {

    /**
     *
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
