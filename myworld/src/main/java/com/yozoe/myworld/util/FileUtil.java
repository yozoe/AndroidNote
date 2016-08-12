package com.yozoe.myworld.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wangdong on 16/8/12.
 */
public class FileUtil {
    public static String getContent(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }


        try {
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputReader);

            String line = "";

            StringBuilder results = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                results.append(line);
            }
            if(inputStream!=null) {
                inputStream.close();
            }
            if (inputReader != null) {
                inputReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return results.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
