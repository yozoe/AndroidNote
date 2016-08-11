package com.yozoe.myworld.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.yozoe.common.L;

/**
 * Created by wangdong on 16/8/8.
 */
public class BaseActivity extends FragmentActivity {

    protected BaseActivity mContext = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
    }

    public void l(String msg) {
        L.d(getTagName(), msg);
    }

    public String getTagName() {
        return "myworld";
    }
}
