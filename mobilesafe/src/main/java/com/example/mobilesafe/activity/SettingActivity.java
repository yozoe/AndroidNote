package com.example.mobilesafe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.ConstantValue;
import com.example.mobilesafe.util.SpUtil;
import com.example.mobilesafe.view.SettingItemVIew;

/**
 * Created by wangdong on 16/8/3.
 */
public class SettingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUpdate();
    }

    private void initUpdate() {
        final SettingItemVIew siv_update = (SettingItemVIew) findViewById(R.id.siv_update);

        boolean open_update = SpUtil.getBoolean(this, ConstantValue.OPEN_UPDATE, false);
        //是否选中根据上一次存储的结果去做决定
        siv_update.setCheck(open_update);

        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = siv_update.isCheck();
                siv_update.setCheck(!isCheck);
                //将取反后的状态存储到相应sp种
                SpUtil.putBoolean(getApplicationContext(), ConstantValue.OPEN_UPDATE, !isCheck);
            }
        });
    }
}
