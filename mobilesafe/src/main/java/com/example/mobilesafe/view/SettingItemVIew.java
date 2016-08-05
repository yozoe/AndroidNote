package com.example.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobilesafe.R;

/**
 * Created by wangdong on 16/8/3.
 */
public class SettingItemVIew extends RelativeLayout {

    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.example.mobilesafe";

    private final CheckBox cb_box;
    private final TextView tv_des;
    private String mDestitle;
    private String mDesoff;
    private String mDeson;

    public SettingItemVIew(Context context) {
        this(context, null);
    }

    public SettingItemVIew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //xml--->view 将设置界面的一个条转换成view对象
        View.inflate(context, R.layout.setting_item_view, this);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);
        cb_box = (CheckBox) findViewById(R.id.cb_box);

        //获取自定义以及原生属性的操作,写在此处,tAttributeSet attrs对象中获取
        initAttrs(attrs);

        tv_title.setText(mDestitle);
    }

    /**
     * 反悔属性集合中自定义属性属性值
     * @param attrs
     */
    private void initAttrs(AttributeSet attrs) {
        //获取属性总个数
//        Log.i("hehe", attrs.getAttributeCount() + "");
        //获取属性名称以及属性值
//        for (int i = 0; i < attrs.getAttributeCount(); i++) {
//            Log.i("hehe", "name " + attrs.getAttributeName(i));
//            Log.i("hehe", "value " + attrs.getAttributeValue(i));
//        }
        mDestitle = attrs.getAttributeValue(NAMESPACE, "destitle");
        mDesoff = attrs.getAttributeValue(NAMESPACE, "desoff");
        mDeson = attrs.getAttributeValue(NAMESPACE, "deson");

        Log.i("hehe", mDestitle);
        Log.i("hehe", mDesoff);
        Log.i("hehe", mDeson);
    }

    /**
     * 判断是否开启的方法
     * @return 返回当前SettingItemView是否选中状态 true开启(checkBox) false关闭()
     */
    public boolean isCheck() {
        return cb_box.isChecked();
    }

    public void setCheck(boolean isCheck) {
        cb_box.setChecked(isCheck);
        if (isCheck) {
            tv_des.setText(mDeson);
        }
        else {
            tv_des.setText(mDesoff);
        }
    }

}