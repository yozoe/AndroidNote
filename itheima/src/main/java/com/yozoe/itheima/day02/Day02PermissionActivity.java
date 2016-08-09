package com.yozoe.itheima.day02;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.io.IOException;

/**
 * Created by wangdong on 16/8/8.
 */
public class Day02PermissionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.openFileOutput("private.txt", Context.MODE_PRIVATE).write("我是private".getBytes());
            this.openFileOutput("private.txt", Context.MODE_APPEND).write("我是append".getBytes());
            this.openFileOutput("private.txt", Context.MODE_WORLD_WRITEABLE).write("我是write".getBytes());
            this.openFileOutput("private.txt", Context.MODE_WORLD_READABLE).write("我是read".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
/*
1位代表是文件的类型
d:文件夹
l:快捷方式
-:文件

2-4位该文件所属用户对本文件的权限
5-7该文件所属用户组对该文件的权限
8-10其他用户对该文件的权限
 */