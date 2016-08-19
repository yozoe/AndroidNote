package com.example.mobilesafe.engine;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by wangdong on 16/8/18.
 */
public class AddressDao {
    //1.指定访问数据库的路径
    public static String path = "/data/data/com.example.mobilesafe/files/address.db";

    //传递一个电话号码,开启数据库连接进行访问,返回一个归属地

    /**
     * 传递一个电话号码,开启数据连接,进行访问,返回一个归属地
     * @param phone
     */
    public static void getAddress(String phone) {
        phone = phone.substring(0, 7);
        //2.开启数据库链接,进行访问
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        //3.数据库查询
        Cursor cursor = db.query("data1", new String[]{"outkey"}, "id = ?", new String[]{phone}, null, null, null);
        //4.查到即可
        if (cursor.moveToNext()) {
            String outkey = cursor.getString(0);
            Log.i("hehe", "outkey = " + outkey);
        }
    }
}
