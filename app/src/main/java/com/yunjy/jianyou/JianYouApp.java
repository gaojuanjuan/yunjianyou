package com.yunjy.jianyou;

import android.app.Application;
import android.content.Context;

import com.yunjy.jianyou.integration.AppDelegate;


/**
 * Created by zt on 2017/9/18.
 */

public class JianYouApp extends Application {

    public static Context mContext;
    AppDelegate appDelegate;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        ConfigGlobal.pack_Name = getPackageName();
         appDelegate = new AppDelegate(this);
    }



}
