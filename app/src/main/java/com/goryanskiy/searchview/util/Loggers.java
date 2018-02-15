package com.goryanskiy.searchview.util;


import android.util.Log;

import com.goryanskiy.searchview.BuildConfig;


public final class Loggers {
    private Loggers(){
    }

    public static void i(String tag, String msg){
        if (BuildConfig.DEBUG) Log.i(tag, msg);
    }
    public static void i(String tag, String msg, Throwable t) {
        if (BuildConfig.DEBUG) Log.i(tag, msg, t);
    }

    public static void e(String tag, String msg){
        Log.e(tag, msg);
    }
    public static void e(String tag, String msg, Throwable t){
        Log.e(tag, msg, t);
    }
}