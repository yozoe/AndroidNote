package com.yozoe.myworld.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yozoe.common.Utils;
import com.yozoe.myworld.R;
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
    private List<View> mModuleViews = null;

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
        mModuleViews = new ArrayList<>();
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

        setVisibility(VISIBLE);
        mColumns = mHomeConfigModule.getColumns() > 0 ? mHomeConfigModule.getColumns() : mColumns;
        mRows = mHomeConfigModule.getRows() > 0 ? mHomeConfigModule.getRows() : mRows;

        mCellPadding = Utils.dip2px(mHomeConfigModule.getCellPadding() < 0 ? mCellPadding : mHomeConfigModule.getCellPadding());
        mCellWidth = (Utils.getScreenWidth() - (mColumns + 1) * mCellPadding) / (float)mColumns;
        mCellHeight = (int) (mCellWidth * mHomeConfigModule.getCellRatio());
        mModuleWidth = calcModuleWidth((int)mCellWidth, mCellPadding);

        //setbackgournd

        List<HomeConfigCell> cells = mHomeConfigModule.getCells();
        if (!Utils.isEmpty(cells)) {
            for (HomeConfigCell cell : cells) {
                View view = mLayoutInflater.inflate(R.layout.view_home_block_cell, null);
                view.setTag(cell);
                addView(view);
                view.setBackgroundColor(Color.parseColor("#ff7755"));
                mModuleViews.add(view);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("hehe", "home block cells layout on measure");

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            Object tag = childView.getTag();

            int cellWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY);
            int cellHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY);
            if(tag!=null && tag instanceof HomeConfigCell) {
                HomeConfigCell cell = (HomeConfigCell)tag;
                Rect rect = calcCellSize(cell);
                // 创建测量参数
                cellWidthSpec = MeasureSpec.makeMeasureSpec(rect.right-rect.left, MeasureSpec.EXACTLY);
                cellHeightSpec = MeasureSpec.makeMeasureSpec(rect.bottom - rect.top, MeasureSpec.EXACTLY);
                childView.measure(cellWidthSpec, cellHeightSpec);
            }
            childView.measure(cellWidthSpec, cellHeightSpec);
        }
        setMeasuredDimension(resolveSize(mModuleWidth, widthMeasureSpec),
                resolveSize(((int) mCellHeight + mCellPadding) * mRows + mCellPadding, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        Log.i("hehe", "home block cells layout on layout");
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++) {
            View childView = getChildAt(i);
            Object tag = childView.getTag();
            if(tag!=null && tag instanceof HomeConfigCell) {
                HomeConfigCell cell = (HomeConfigCell)tag;
                Rect rect = calcCellSize(cell);
                childView.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    private int calcModuleWidth(int cellWidth, int cellPadding) {
        int totalColumns = calcModuleTotalColumns();
        if (totalColumns == mHomeConfigModule.getColumns()) {
            return Utils.getScreenWidth();
        }
        return 0;
    }

    /**
     *
     * @return
     */
    private int calcModuleTotalColumns() {
        List<HomeConfigCell> cells = mHomeConfigModule.getCells();
        int columns = 0;
        if (Utils.isEmpty(cells)) {
            for (HomeConfigCell cell : cells) {
                int thisColumns = cell.getPositionX() + cell.getCellColumns();
                columns = Math.max(columns, thisColumns);
            }
        }
        return columns;
    }

    private Rect calcCellSize(HomeConfigCell cell) {
        int cellColumns = cell.getCellColumns();
        float cellWidth = 0;
        if (cellColumns > 0) {
            cellWidth = cellColumns * mCellWidth + (cellColumns - 1) * mCellPadding;
        }
        int cellRows = cell.getCellRows();
        float cellHeight = 0;
        if (cellRows > 0) {
            cellHeight = cellRows * mCellHeight + (cellRows - 1) * mCellPadding;
        }
        int x = (int)((cell.getPositionX() + 1) * mCellPadding + cell.getPositionX() * mCellWidth);
        int y = (int)((cell.getPositionY() + 1) * mCellPadding + cell.getPositionY() * mCellHeight);
        Rect rect = new Rect(x, y, x+(int)cellWidth, y+(int)cellHeight);
        return rect;
    }

}
