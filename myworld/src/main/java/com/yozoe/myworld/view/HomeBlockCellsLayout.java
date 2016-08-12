package com.yozoe.myworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.yozoe.myworld.entity.HomeConfigEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdong on 16/8/12.
 */
public class HomeBlockCellsLayout extends HomeBaseLayout {

    private HomeConfigEntity.HomeConfigModule mHomeConfigModule = null;
    private List<View> mModulesViews = null;

    private int mColumns = 1;
    private int mRows = 1;
    private float mCellWidth = 0;
    private float mCellHeight = 0;
    private int mCellPadding = 0;
    private int mModuleWidth = 0;

    public HomeBlockCellsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mModulesViews = new ArrayList<>();
    }

    public HomeBlockCellsLayout(Context context) {
        this(context, null);
    }

    public HomeConfigEntity.HomeConfigModule getmHomeConfigModule() {
        return mHomeConfigModule;
    }

    public void setmHomeConfigModule(HomeConfigEntity.HomeConfigModule mHomeConfigModule) {
        this.mHomeConfigModule = mHomeConfigModule;
        initLayout();
    }

    private void initLayout() {

    }

    private int calcModulewidth() {
        return 0;
    }

}
