package com.example.mobilesafe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobilesafe.R;
import com.example.mobilesafe.engine.AddressDao;


/**
 * Created by wangdong on 16/8/15.
 */
public class QueryAddressActivity extends Activity {
    private EditText et_phone;
    private Button bt_query;
    private TextView tv_result ;
    private String mAddress;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv_result.setText(mAddress);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_address);

        initUI();
    }

    private void initUI() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        bt_query = (Button) findViewById(R.id.bt_query);
        tv_result = (TextView) findViewById(R.id.tv_result);

        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_phone.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    //查询是耗时操作,开启子线程
                    query(phone);
                }
                else {
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    //interpolator插补器,数据函数
                    //自定义插补器
//                    shake.setInterpolator(new Interpolator() {
//                        @Override
//                        public float getInterpolation(float input) {
//                            return 0;
//                        }
//                    });

                    et_phone.setAnimation(shake);
                }
            }
        });

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = et_phone.getText().toString();
                query(phone);
            }
        });
    }

    protected void query(final String phone) {
         new Thread() {
             @Override
             public void run() {
                 mAddress = AddressDao.getAddress(phone);
                 mHandler.sendEmptyMessage(0);
             }
         }.start();
    }
}
