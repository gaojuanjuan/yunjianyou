package com.yunjy.jianyou.integration;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Environment;
import android.util.Log;


import com.blankj.utilcode.util.Utils;
import com.yunjy.jianyou.ConfigGlobal;
import com.yunjy.jianyou.tools.DateUtil;
import com.yunjy.jianyou.tools.LogUtils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;

import java.io.File;
import java.io.IOException;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by zt on 2017/9/18.
 */

public class AppDelegate {


    AppActivityManager   mAppActivityManager;

    public AppDelegate(Application mApp) {

        mApp.registerComponentCallbacks(new mComponentCallbacks2());
        Utils.init(mApp);
       // initLogger(getLogPath());
        mAppActivityManager = new AppActivityManager(mApp);
        LogUtils.i("mAppDelegate","mAppDelegate create ! ");
        mApp.registerActivityLifecycleCallbacks(new ActivityLifecycle(mAppActivityManager,mApp));

    }



    public void initOkgo(){




    }

    public void initLogger(String path){

        String day = "log_"+ DateUtil.getDay()+".txt";
        String fileName = path + "/"+ day;
        Log.i("AppDelegateImpl","fileName "+fileName);
        try {
            FileUtils.touch(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(fileName);
        logConfigurator.setRootLevel(Level.ALL);
        logConfigurator.setLevel("org.apache", Level.ALL);

        logConfigurator.setFilePattern("%d %-5p [%c{4}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(Long.MAX_VALUE);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();

    }

    public String getLogPath() {
        File externalStorageDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        File file = new File(externalStorageDirectory, ConfigGlobal.pack_Name);
        if(!file.exists())file.mkdir();
        return file.getAbsolutePath();
    }

    class mComponentCallbacks2 implements ComponentCallbacks2 {


        @Override
        public void onTrimMemory(int level) {

        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {

        }

        @Override
        public void onLowMemory() {



        }
    }

}
