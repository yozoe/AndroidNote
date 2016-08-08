package com.yozoe.myworld.activity;

/**
 * Created by wangdong on 16/8/8.
 */
public class BaseMWActivity extends BaseActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initHeader();
        initViews();
        initData();
    }

    protected void initHeader() {

    }

    protected void initViews() {

    }

    protected void initData() {

    }
}
