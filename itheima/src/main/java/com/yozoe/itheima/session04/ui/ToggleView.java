package com.yozoe.itheima.session04.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义开关
 * Created by wangdong on 16/8/25.
 * Android 的界面绘制流程
 * 测量          摆放         绘制
 * measure   -> layout   -> draw
 * onMeasure -> onLayout -> onDraw 重写这些方法,实现自定义空间
 *
 * onResume() 之后执行
 *
 * View
 * onMeasure() (在这个方法里指定自己的狂宽高) -> onDraw() (绘制自己的内容)
 *
 * ViewGroup
 * onMeasure() (指定自己的宽高,所有子view的高度) -> onLayout() (摆放所有子view) -> onDraw(绘制内容)
 */
public class ToggleView extends View {

    private Bitmap switchBackgroundBitmap;  //背景图片
    private Bitmap slideButtonBitmap;   //滑块图片
    private Paint paint;
    private boolean mSwitchState = false; //开关状态,默认false

    /**
     * 用于代码创建控件
     * @param context
     */
    public ToggleView(Context context) {
        super(context);
        init();
    }

    /**
     * 用于在xml里使用,可指定自定义属性
     * @param context
     * @param attrs
     */
    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    /**
     * 用于在xml里使用,可指定自定义属性,如果制定了样式,则走此构造函数
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(switchBackgroundBitmap.getWidth(), switchBackgroundBitmap.getHeight());
    }

    //Canvas 画布,画板. 在上边绘制的内容都会显示到界面上
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.绘制背景 尽量不要在onDraw里面创建

        if (mSwitchState) {
            int newLeft = switchBackgroundBitmap.getWidth() - slideButtonBitmap.getWidth();
            canvas.drawBitmap(slideButtonBitmap, newLeft, 0, paint);
        }
        else {
            canvas.drawBitmap(slideButtonBitmap, 0, 0, paint);
        }

        canvas.drawBitmap(switchBackgroundBitmap, 0, 0, paint);

        //2.绘制滑块
        canvas.drawBitmap(slideButtonBitmap, 0, 0, paint);
    }

    /**
     * 设置背景图
     * @param switchBackground
     */
    public void setSwitchBackgroundResource(int switchBackground) {
        switchBackgroundBitmap = BitmapFactory.decodeResource(getResources(), switchBackground);
    }

    /**
     * 设置滑块图片资源
     * @param slideButton
     */
    public void setSlideButtonResource(int slideButton) {
        slideButtonBitmap = BitmapFactory.decodeResource(getResources(), slideButton);
    }

    /**
     * 设置开关状态
     * @param mSwitchState
     */
    public void setSwitchState(boolean mSwitchState) {
        this.mSwitchState = mSwitchState;
    }
}
