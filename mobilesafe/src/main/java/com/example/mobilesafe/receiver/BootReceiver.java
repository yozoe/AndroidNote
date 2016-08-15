package com.example.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.mobilesafe.util.ConstantValue;
import com.example.mobilesafe.util.SpUtil;

/**
 * Created by wangdong on 16/8/13.
 */
public class BootReceiver extends BroadcastReceiver {

    private static final String tag = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(tag, "重启手机成功了, 并且监听到了响应的广播");

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = tm.getSimSerialNumber();
        String sim_number = SpUtil.getString(context, ConstantValue.SIM_NUMBER, "");
        if (!simSerialNumber.equals(sim_number)) {
            SmsManager sms = SmsManager.getDefault();
            /**
             * 参数1:
             * 参数2:短信中心,没用
             * 参数3:短信内容(模拟器不支持中文)
             * 参数4:送达意图
             * 参数5:送达返回意图
             */
            sms.sendTextMessage("13800138000", null, "sim change!!!", null, null);
        }
    }
}
