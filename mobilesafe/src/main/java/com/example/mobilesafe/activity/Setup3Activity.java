package com.example.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.ConstantValue;
import com.example.mobilesafe.util.SpUtil;
import com.example.mobilesafe.util.ToastUtil;

/**
 * Created by wangdong on 16/8/7.
 */
public class Setup3Activity extends BaseSetupActivity {
    private EditText et_phone_number;
    private Button bt_select_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);

        initUI();
    }

    @Override
    protected void showNextPage() {
        //点击按钮以后,需要获取输入框中的联系人,再做下一步操作
        String phone = et_phone_number.getText().toString();

        //在sp存储了相关联系人以后才可以跳转到下一个界面
//        String contact_phone = SpUtil.getString(getApplicationContext(), ConstantValue.CONTACT_PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(getApplication(), Setup4Activity.class);
            startActivity(intent);
            finish();

            //如果现在是输入电话号码,则需要去保存
            overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
        }
        else {
            ToastUtil.show(this, "请输入电话号码");
        }
    }

    @Override
    protected void showPrePage() {
        Intent intent = new Intent(getApplication(), Setup2Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
    }

    private void initUI() {
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        String phone = SpUtil.getString(this, ConstantValue.CONTACT_PHONE, "");
        et_phone_number.setText(phone);
        bt_select_number = (Button) findViewById(R.id.bt_select_number);
        bt_select_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //讲特殊字符过滤
        if (data != null) {
            String phone = data.getStringExtra("phone");
            phone = phone.replace("-", "").replace(" ", "").trim();
            et_phone_number.setText(phone);
            //存储联系人至sp中
            SpUtil.putString(getApplicationContext(), ConstantValue.CONTACT_PHONE, phone);
        }
    }

}
