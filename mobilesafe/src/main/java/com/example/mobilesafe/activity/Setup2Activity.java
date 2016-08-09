package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.ConstantValue;
import com.example.mobilesafe.util.SpUtil;
import com.example.mobilesafe.util.ToastUtil;
import com.example.mobilesafe.view.SettingItemVIew;

/**
 * Created by wangdong on 16/8/7.
 */
public class Setup2Activity extends Activity {
    private SettingItemVIew siv_sim_bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setup2);
        initUI();
    }

    private void initUI() {
        siv_sim_bound = (SettingItemVIew) findViewById(R.id.siv_sim_bound);
        //1.回显(读取已有的绑定状态,用作显示,sp中是否存储了sim卡的序列号)
        String sim_number = SpUtil.getString(this, ConstantValue.SIM_NUMBERE, "");
        //2.判断是否序列号为""
        if (TextUtils.isEmpty(sim_number)) {
            siv_sim_bound.setCheck(false);
        }
        else {
            siv_sim_bound.setCheck(true);
        }

        siv_sim_bound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //3.获取原有状态
                boolean isCheck = siv_sim_bound.isCheck();
                //4.将原有状态取反
                //5.设置给带给你钱条目
                siv_sim_bound.setCheck(!isCheck);

                if (!isCheck) {
                    //6.存储(序列卡号)
                    //6.1获取对应sim卡序列号TelephoneManager
                    TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    //6.2获取sim卡的序列卡号
                    String simSerialNumber = manager.getSimSerialNumber();
                    //6.3
                    SpUtil.putString(getApplicationContext(), ConstantValue.SIM_NUMBERE, simSerialNumber);
                }
                else {
                    //7.讲存储序列卡号的节点从sp中删掉
                    SpUtil.remove(getApplicationContext(), ConstantValue.SIM_NUMBERE);
                }
            }
        });
    }

    public void nextPage(View view) {

        Intent intent = new Intent(getApplication(), Setup3Activity.class);
        startActivity(intent);
        finish();

//        先跳过绑定sim卡
//        String serialNumber =  SpUtil.getString(this, ConstantValue.SIM_NUMBERE, "");
//        if (!TextUtils.isEmpty(serialNumber)) {
//            Intent intent = new Intent(getApplication(), Setup3Activity.class);
//            startActivity(intent);
//            finish();
//        }
//        else {
//            ToastUtil.show(this, "请绑定sim卡");
//        }

    }

    public void prePage(View view) {
        Intent intent = new Intent(getApplication(), Setup1Activity.class);
        startActivity(intent);
        finish();
    }
}
