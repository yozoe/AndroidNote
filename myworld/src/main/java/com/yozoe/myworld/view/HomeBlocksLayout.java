package com.yozoe.myworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yozoe.myworld.R;
import com.yozoe.myworld.entity.HomeConfigEntity;
import com.yozoe.myworld.fragment.FirstFragment;

/**
 * Created by wangdong on 16/8/12.
 */
public class HomeBlocksLayout extends HomeBaseLayoutWithTitle {

    private HomeBlockCellsLayout mLayoutBlocksCells = null;

    public HomeBlocksLayout(Context context) {
        this(context, null);
    }

    public HomeBlocksLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater inflate = LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.view_home_blocks, this);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mRlTitle = (RelativeLayout) view.findViewById(R.id.rl_title);
        mExtraTitle = (TextView) view.findViewById(R.id.tv_extra);
        mLayoutBlocksCells = (HomeBlockCellsLayout) view.findViewById(R.id.layout_block_cells);
        mTitleClickBlock = (LinearLayout) view.findViewById(R.id.ll_title);
    }

    @Override
    public void setModule(HomeConfigEntity.HomeConfigModule mHomeConfigModule) {
        this.mHomeConfigModule = mHomeConfigModule;
        setModuleTitle(mHomeConfigModule == null ? null : mHomeConfigModule.getTitle());
        mLayoutBlocksCells.setModule(mHomeConfigModule);
    }
}
