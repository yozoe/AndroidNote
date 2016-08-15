package com.yozoe.myworld.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yozoe.common.Utils;
import com.yozoe.myworld.entity.HomeConfigEntity;
import com.yozoe.myworld.entity.HomeConfigEntity.HomeConfigModule;
import com.yozoe.myworld.entity.HomeConfigEntity.HomeConfigCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdong on 16/8/12.
 */
public class HomeBlockCellsLayout extends HomeBaseLayout {

    private HomeConfigModule mHomeConfigModule = null;
    private List<View> mModulesViews = null;

    private int mColumns = 1;
    private int mRows = 1;
    private float mCellWidth = 0;
    private float mCellHeight = 0;
    private int mCellPadding = 0;
    private int mModuleWidth = 0;

    public HomeBlockCellsLayout(Context context) {
        this(context, null);
    }

    public HomeBlockCellsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mModulesViews = new ArrayList<>();
    }

    public HomeConfigModule getmHomeConfigModule() {
        return mHomeConfigModule;
    }

    public void setModule(HomeConfigModule module) {
        this.mHomeConfigModule = module;
        initLayout();
    }

    private void initLayout() {
        Log.i("hehe", "home block cells layout init layout");
    }

    private int calcModulewidth() {
        return 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("hehe", "home block cells layout on measure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        Log.i("hehe", "home block cells layout on layout");
    }

    private int calcModuleWidth(int cellWidth, int cellPadding) {
        int totalColumns = calcModuleTotalColumns();
        if (totalColumns == mHomeConfigModule.getColumns()) {
            return Utils.getScreenWidth();
        }
        return 0;
    }

    private int calcModuleTotalColumns() {
        List<HomeConfigCell> cells = mHomeConfigModule.getCells();
        int columns = 0;
        if (Utils.isEmpty(cells)) {

        }
        return 0;
    }

    private Rect calcCellSize() {

        return null;
    }

}
