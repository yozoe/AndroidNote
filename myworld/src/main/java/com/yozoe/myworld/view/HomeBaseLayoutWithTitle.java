package com.yozoe.myworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yozoe.common.Utils;
import com.yozoe.myworld.entity.HomeConfigEntity;

/**
 * Created by wangdong on 16/8/12.
 */
public class HomeBaseLayoutWithTitle extends LinearLayout {

    protected Context mContext;
    protected HomeConfigEntity.HomeConfigModule mHomeConfigModule;
    protected TextView mTvTitle;
    protected RelativeLayout mRlTitle;
    protected TextView mExtraTitle;
    protected LinearLayout mTitleClickBlock;

    public HomeBaseLayoutWithTitle(Context context) {
        super(context);
    }

    public HomeBaseLayoutWithTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeConfigEntity.HomeConfigModule getModule() {
        return mHomeConfigModule;
    }

    public void setModule(HomeConfigEntity.HomeConfigModule mHomeConfigModule) {
        this.mHomeConfigModule = mHomeConfigModule;
    }

    protected void setModuleTitle(String title) {
        if (Utils.isEmpty(title)) {
            mRlTitle.setVisibility(View.GONE);
        }
        else {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(title);
            if (mHomeConfigModule != null && Utils.isEmpty(mHomeConfigModule.getSubTitle())) {
                mExtraTitle.setVisibility(View.INVISIBLE);
            }
            else {
                mExtraTitle.setText(mHomeConfigModule.getSubTitle());
            }
        }
    }
}
