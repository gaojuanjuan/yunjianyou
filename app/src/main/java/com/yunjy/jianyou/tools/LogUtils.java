package com.yunjy.jianyou.tools;

import android.text.TextUtils;
import android.util.Log;

import com.yunjy.jianyou.ConfigGlobal;

import org.apache.log4j.Logger;

/**
 * Created by zt on 2017/6/27.
 */

public class LogUtils {

    private static boolean islog4j = false;

    public static void i(Class clazz, String msg) {
        i(clazz.getName(), msg);
    }


    public static void e(Class clazz, String msg) {
        e(clazz.getName(), msg);
    }

    public static void warn(Class clazz, String msg) {
        e(clazz.getName(), msg);
    }


    public static void i(Object obtag, String msg) {
        i(obtag.getClass().getName(), msg);
    }


    public static void e(Object obtag, String msg) {
        e(obtag.getClass().getName(), msg);
    }

    public static void warn(Object obtag, String msg) {
        e(obtag.getClass().getName(), msg);
    }

    public static void i(String msg) {
        i(ConfigGlobal.pack_Name, msg);
    }

    public static void e(String msg) {
        e(ConfigGlobal.pack_Name, msg);
    }

    public static void e(Throwable e) {
        e(ConfigGlobal.pack_Name, e.getMessage());
    }

    public static void warn(String msg) {
        warn(ConfigGlobal.pack_Name, msg);
    }

    public static void i(String tag, String msg) {
        if (ConfigGlobal.isDebug()) {
            if (islog4j)
                getLogger(tag).info(msg);
            else
                Log.i(tag, tag + " : " + msg);
        }
    }


    public static void e(String tag, String msg) {
        if (ConfigGlobal.isDebug()) {
            if (islog4j)
                getLogger(tag).error(msg);
            else
                Log.e(tag, tag + " : " + msg);
        }
    }

    public static void e(String tag, Throwable e) {

        if (ConfigGlobal.isDebug()) {
            if (islog4j)
                getLogger(tag).error("", e);
            else
                Log.e(tag, tag + " : " + e.getMessage());
        }

    }


    public static void warn(String tag, String msg) {
        if (ConfigGlobal.isDebug()) {
            if (islog4j)
                getLogger(tag).warn(msg);
            else
                Log.w(tag, tag + " : " + msg);
        }
    }

    public static Logger getLogger(String tag) {

        if (TextUtils.isEmpty(tag)) {
            return Logger.getRootLogger();
        } else {
            return Logger.getLogger(tag);

        }


    }


}
