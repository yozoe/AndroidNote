package com.yozoe.myworld.activity;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yozoe.myworld.R;
import com.yozoe.myworld.fragment.BaseFragment;
import com.yozoe.myworld.fragment.FirstFragment;
import com.yozoe.myworld.fragment.FourthFragment;
import com.yozoe.myworld.fragment.SecondFragment;
import com.yozoe.myworld.fragment.ThirdFragment;
import com.yozoe.myworld.view.TabbarItemLayout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyWorldActivity extends BaseMWActivity {

    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;
    private ThirdFragment mThirdFragment;
    private FourthFragment mFourthFragment;

    public static final int TABBAR_FIRST = 0x0000;
    public static final int TABBAR_SECOND = 0x0001;
    public static final int TABBAR_THIRD = 0x0002;
    public static final int TABBAR_FOURTH = 0x0003;

    private TabbarItemLayout mFirsetTabbar;
    private TabbarItemLayout mSecondTabbar;
    private TabbarItemLayout mThirdTabbar;
    private TabbarItemLayout mFourthTabbar;

    private Map<Integer, BaseFragment> mFragments;

    private int mSelectedTabbar = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_world);

        choseMenu();
    }

    private void choseMenu() {
        selectedTabbar(TABBAR_FIRST);
    }

    @Override
    protected void initData() {
        initFragment();
    }

    private void initFragment() {
        mFragments = new HashMap<>(4);
        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();
        mFourthFragment = new FourthFragment();
        mFragments.put(TABBAR_FIRST, mFirstFragment);
        mFragments.put(TABBAR_SECOND, mSecondFragment);
        mFragments.put(TABBAR_THIRD, mThirdFragment);
        mFragments.put(TABBAR_FOURTH, mFourthFragment);
    }

    @Override
    protected void initHeader() {

    }

    @Override
    protected void initViews() {
        mFirsetTabbar = (TabbarItemLayout) findViewById(R.id.v_first_tab);
        mSecondTabbar = (TabbarItemLayout) findViewById(R.id.v_second_tab);
        mThirdTabbar = (TabbarItemLayout) findViewById(R.id.v_third_tab);
        mFourthTabbar = (TabbarItemLayout) findViewById(R.id.v_fourth_tab);
    }

    public void onFirstTabClick(View v) {
        l("first");
        selectedTabbar(TABBAR_FIRST);
    }

    public void onSecondTabClick(View v) {
        l("second");
        selectedTabbar(TABBAR_SECOND);
    }

    public void onThirdTabClick(View v) {
        l("third");
        selectedTabbar(TABBAR_THIRD);
    }

    public void onFourthTabClick(View v) {
        l("fourth");
        selectedTabbar(TABBAR_FOURTH);
    }

    private void selectedTabbar(int tabbar) {
        if (tabbar == mSelectedTabbar) {
            return;
        }
        refreshTabbarStatus(tabbar);
        mSelectedTabbar = tabbar;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Iterator<Integer> iterator = mFragments.keySet().iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            BaseFragment fragment = mFragments.get(key);
            if (key == tabbar) {
                if (!fragment.isAdded()) {
                    ft.add(R.id.layout_content, fragment);
                    ft.show(fragment);
                }
                else {
                    ft.show(fragment);
                }
            }
            else {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    private void refreshTabbarStatus(int tabbar) {
        mFirsetTabbar.setStatus(tabbar == TABBAR_FIRST ? true : false);
        mSecondTabbar.setStatus(tabbar == TABBAR_SECOND ? true : false);
        mThirdTabbar.setStatus(tabbar == TABBAR_THIRD ? true : false);
        mFourthTabbar.setStatus(tabbar == TABBAR_FOURTH ? true : false);
    }
}
