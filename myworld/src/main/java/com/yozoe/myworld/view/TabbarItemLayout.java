package com.yozoe.myworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yozoe.myworld.R;

/**
 * Created by wangdong on 16/8/8.
 */
public class TabbarItemLayout extends LinearLayout {

    private View mView;

    public TabbarItemLayout(Context context) {
        super(context);
    }

    public TabbarItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.view_tabbar_item, null);
        addView(mView);
    }
}
