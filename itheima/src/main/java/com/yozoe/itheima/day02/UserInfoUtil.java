package com.yozoe.itheima.day02;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdong on 16/8/8.
 */
public class UserInfoUtil {

    public static boolean saveUserInfo_android(Context context, String username, String password) {

        try {
            String userinfo = username + "##" + password;

            //得到私有目录下一个文件写入流 name:私有目录文件的名称 mode:文件的操作模式,私有,追加,全局读,全局写
            FileOutputStream fileOutputStream = context.openFileOutput("userinfo.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(userinfo.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean saveUserInfo(Context context, String username, String password) {
//        String path = "/data/data/com.yozoe.itheima/";
//        String path = context.getFilesDir().getPath();
//        String path = "/mnt/sdcard";
        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(path, "userinfo.txt");
        try {
            String userinfo = username + "##" + password;
            FileOutputStream fop = new FileOutputStream(file);
            fop.write(userinfo.getBytes());
            fop.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Map<String, String> getUserInfo_android(Context context) {
        try {

            String path = Environment.getExternalStorageDirectory().getPath();
//            File file = new File(path, "userinfo.txt");
//            FileInputStream fis = new FileInputStream(file);
            FileInputStream fis = context.openFileInput("userinfo.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("##");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("username", split[0]);
            hashMap.put("password", split[1]);
            bufferedReader.close();
            fis.close();
            return hashMap;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getUserInfo(Context context) {
        try {
//            String path = "/data/data/com.yozoe.itheima/";
//            String path = context.getFilesDir().getPath();
//            String path = "/mnt/sdcard";
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path, "userinfo.txt");
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("##");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("username", split[0]);
            hashMap.put("password", split[1]);
            bufferedReader.close();
            fis.close();
            return hashMap;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
