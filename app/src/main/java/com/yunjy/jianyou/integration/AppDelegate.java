package com.yunjy.jianyou.integration;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;


import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.yunjy.jianyou.ConfigGlobal;
import com.yunjy.jianyou.net.SSLSocketFactoryCompat;
import com.yunjy.jianyou.net.TrustAllCerts;
import com.yunjy.jianyou.tools.DateUtil;
import com.yunjy.jianyou.tools.LogUtil;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import de.mindpipe.android.logging.log4j.LogConfigurator;
import okhttp3.OkHttpClient;

/**
 * Created by zt on 2017/9/18.
 */

public class AppDelegate {


    AppActivityManager mAppActivityManager;

    public AppDelegate(@NonNull Application mApp) {

        mApp.registerComponentCallbacks(new mComponentCallbacks2());
        Utils.init(mApp);
        // initLogger(getLogPath());
        mAppActivityManager = new AppActivityManager(mApp);
        LogUtil.i("mAppDelegate", "mAppDelegate create ! ");
        mApp.registerActivityLifecycleCallbacks(new ActivityLifecycle(mAppActivityManager, mApp));
        initOkgo(mApp);
    }


    public void initOkgo(Application mApp) {


        HttpHeaders headers = new HttpHeaders();
       /* headers.put("device", UmAPP_Token);
        headers.put("mac", mac);*/
        headers.put("channel", "AND");
        OkGo.init(mApp);
        LogUtil.i("mAppDelegate", " ---- okgo builder start");

        OkGo instance = OkGo.getInstance();
        if (ConfigGlobal.isDebug()) {
            instance.debug("sayInfo", java.util.logging.Level.INFO, true);
        }
        instance/*.debug("sayInfo", Level.INFO, is_Debug)*/
               /* .setCertificates()  //信任所有证书*/
                .setConnectTimeout(ConfigGlobal.ConnectTimeout)
                .setReadTimeOut(ConfigGlobal.ReadTimeOut)
                .addCommonHeaders(headers)
                .setWriteTimeOut(ConfigGlobal.WriteTimeOut)
                .setCookieStore(new PersistentCookieStore())
                .setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .setRetryCount(0);
        LogUtil.i("mAppDelegate", "----  okgo builder ok ");

        OkHttpClient.Builder okHttpClientBuilder = instance.getOkHttpClientBuilder();
        okHttpClientBuilder.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts());


    }

    private static SSLSocketFactory createSSLSocketFactory() {
        return new SSLSocketFactoryCompat(new TrustAllCerts());
    }

    public void initLogger(String path) {

        String day = "log_" + DateUtil.getDay() + ".txt";
        String fileName = path + "/" + day;
        Log.i("AppDelegateImpl", "fileName " + fileName);
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
        if (!file.exists()) file.mkdir();
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
