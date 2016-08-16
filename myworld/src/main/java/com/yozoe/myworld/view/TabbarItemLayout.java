package com.yozoe.myworld.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yozoe.myworld.R;

/**
 * Created by wangdong on 16/8/8.
 */
public class TabbarItemLayout extends LinearLayout {

    private View mView;
    private TextView mLabelView;
    private ImageView mImageView;
    private String mTabbarLabel;
    private boolean isPressedStatus = false;

    private Drawable mIconDrawable = null;
    private Drawable mIconPressedDrawable = null;

    public TabbarItemLayout(Context context) {
        super(context);
    }

    public TabbarItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.view_tabbar_item, null);

        mLabelView = (TextView) mView.findViewById(R.id.textView);
        mImageView = (ImageView) mView.findViewById(R.id.imageView);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TabbarItemLayout);

        if (array.hasValue(R.styleable.TabbarItemLayout_labelText)) {
            mTabbarLabel = array.getString(R.styleable.TabbarItemLayout_labelText);
        }

        if (array.hasValue(R.styleable.TabbarItemLayout_tabbarIcon)) {
            mIconDrawable = array.getDrawable(R.styleable.TabbarItemLayout_tabbarIcon);
        }

        if (array.hasValue(R.styleable.TabbarItemLayout_tabbarIconPressed)) {
            mIconPressedDrawable = array.getDrawable(R.styleable.TabbarItemLayout_tabbarIconPressed);
        }

        array.recycle();

//        if (!isInEditMode()) {
            setText(mTabbarLabel);
        setStatus(isPressedStatus);
//        }

        addView(mView);
    }

    public void setText(String text) {
        mLabelView.setText(text);
    }

    public void setStatus(boolean isPressed) {
        isPressedStatus = isPressed;
        refreshLayout(isPressedStatus);
    }

    private void refreshLayout(boolean isPressed) {
        mImageView.setImageDrawable(getIconDrawable(isPressed));
    }

    private Drawable getIconDrawable(boolean isPressed) {
        if (isPressed) {
            return mIconPressedDrawable;
        }
        else {
            return mIconDrawable;
        }
    }
}
