package com.example.zhangzhongzheng.retrofit_mvp.utils;

import android.util.Log;

/**
 * Created by zhangzhongzheng on 2016/9/13.
 */
public class MyLogger {
    private boolean isLog = true;

    public void setLog(boolean log) {
        isLog = log;
    }

    public static void log(String tag, String content) {
        Log.i(tag, content);
    }
}
