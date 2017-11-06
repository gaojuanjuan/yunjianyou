package com.yunjy.jianyou.integration;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.yunjy.jianyou.ConfigGlobal;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.tools.LogUtils;
import com.yunjy.jianyou.tools.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zt on 2017/9/18.
 *
 * 统一处理 蓝牙的 断开和提示
 *
 */

public class AppActivityManager {


    public List<Activity> mActivityList;
    private Application mApplication;
    protected Activity mCurrentActivity;

    public final static int START_ACTIVITY = 0;
    public final static int CLOSE_ALL = 1;
    public final static int SNACKBAR_SHOW = 2;
    public final static int TOAST_SHOW = 3;
    public final static int TOP_TOAST_SHOW = 4;
    public final static int EXIT_APP = -100;

    public final static String IS_NOT_ADD_ACTIVITYLIST = "IS_NOT_ADD_ACTIVITY_LIST";


    public AppActivityManager(Application mApp) {
        this.mApplication = mApp;
        mActivityList = new LinkedList<>();
        EventBus.getDefault().register(this);

    }


    public Activity getmCurrentActivity() {
        return this.mCurrentActivity;
    }


    @Subscribe
    public void onMessageEvent(Message message) {

        switch (message.what) {

            case START_ACTIVITY:
                dispatchStartActivity(message);
                break;

            case CLOSE_ALL:
                closeAll();
                break;

            case SNACKBAR_SHOW:
                showSnackBar(message);
                break;

            case TOAST_SHOW:
                showToast(message);
                break;

            case TOP_TOAST_SHOW:
                showTopToast(message);
                break;
            case EXIT_APP:
                exit();
                break;

        }
    }




    public void showSnackBar(Message msg) {
        if (mCurrentActivity != null && msg.obj != null) {
            String show_content = (String) msg.obj;
            View viewById = mCurrentActivity.getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(viewById, show_content, Snackbar.LENGTH_SHORT).show();
        }
    }

    public void showToast(Message msg) {
        if (mCurrentActivity != null && msg.obj != null) {
            String show_content = (String) msg.obj;
            Toast.makeText(mCurrentActivity, show_content, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * TopToast的背景颜色是根据主题的 colorPrimary 来设置的
     * 如果你设置的主题 没有设置colorPrimary 那么 light 的话 背景是白的
     * dark 是灰的 。。。
     *
     * 不过你也可以自己改布局mtoast_layout的布局。。。
     *
     */
    Toast mTopToast;
    public void showTopToast(Message msg){
        String show_content = (String) msg.obj;
        if(mTopToast == null ){
            mTopToast = Toast.makeText(mApplication, "", Toast.LENGTH_SHORT);
        }
        View inflate = View.inflate(mCurrentActivity, R.layout.mtoast_layout, null);
        ((TextView)inflate.findViewById(R.id.toast_text)).setText(show_content);
        mTopToast .setView(inflate);
        /*android.R.style.Animation_Translucent 为 系统提供的右进右出的动画*/
        ToastUtils.setToastAnimation(mTopToast,R.style.mToast_Animation_Translucent);
        mTopToast.show();
    }


    public void dispatchStartActivity(Message msg) {

        if (msg.obj instanceof Intent) {
            startActivity((Intent) msg.obj);
        } else if (msg.obj instanceof Class) {
            startActivity((Class) msg.obj);
        }

    }


    public void startActivity(Intent intent) {
        if (mCurrentActivity != null) {
            mCurrentActivity.startActivity(intent);
        } else {
            LogUtils.warn(" eventbus startActivity .. mCurrent is null NEW_TASK startActivity");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
        }
    }


    public void startActivity(Class startActivityClass) {

        startActivity(new Intent(mApplication, startActivityClass));

    }


    /**
     * 添加一个Activity到LinkedList里面
     */
    public void addActivity(Activity activity) {

        if (mActivityList == null) mActivityList = new LinkedList<>();
        synchronized (AppActivityManager.class) {
            if (!mActivityList.contains(activity)) {
                mActivityList.add(activity);
                mCurrentActivity = activity;
            }
        }
    }

    /**
     * 删除一个Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        synchronized (AppActivityManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }


    public void closeAll() {
        Iterator<Activity> iterator = mActivityList.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            iterator.remove();
            next.finish();
        }
    }


    public List<Activity> getActivityList() {

        return this.mActivityList;

    }

    public void exit() {
        try {
            LogUtils.warn(ConfigGlobal.pack_Name+" exit...");
            EventBus.getDefault().unregister(this);
            closeAll();
            ActivityManager ActivityManager = (android.app.ActivityManager) mApplication.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.killBackgroundProcesses(mApplication.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
