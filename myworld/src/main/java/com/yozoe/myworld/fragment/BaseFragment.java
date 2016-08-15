package com.yozoe.myworld.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.yozoe.myworld.activity.BaseActivity;

/**
 * Created by wangdong on 16/8/8.
 */
public class BaseFragment extends Fragment {
    protected BaseActivity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseActivity) getActivity();
    }
}
