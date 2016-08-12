package com.yozoe.myworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by wangdong on 16/8/12.
 */
public class HomeBaseLayout extends FrameLayout {

    LayoutInflater mLayoutInflater = null;

    public HomeBaseLayout(Context context) {
        this(context, null);
    }

    public HomeBaseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

}
