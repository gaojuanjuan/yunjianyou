package com.yunjy.jianyou.integration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.yunjy.jianyou.base.IActivity;
import com.yunjy.jianyou.tools.LogUtil;


/**
 * Created by zt on 2017/9/18.
 */

public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private AppActivityManager am;
    private Application mApplication;
    private  FragmentLifecycle mFragmentLifecycle;

    public ActivityLifecycle(@NonNull  AppActivityManager am,@NonNull Application mApplication) {
        this.am = am;
        this.mApplication = mApplication;
        mFragmentLifecycle = new FragmentLifecycle();

    }


    @Override
    @CallSuper
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        LogUtil.i(activity.toString()," onActivityCreated .. ");
        boolean booleanExtra = activity.getIntent().getBooleanExtra(AppActivityManager.IS_NOT_ADD_ACTIVITYLIST, false);

        if(!booleanExtra){
            am.addActivity(activity);
        }

        boolean b = activity instanceof IActivity ? ((IActivity)activity).userFragment() : false;
        if(activity instanceof FragmentActivity || b){
            ((FragmentActivity)activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(mFragmentLifecycle,true);
        }

    }

    @Override
    @CallSuper
    public void onActivityStarted(Activity activity) {

    }

    @Override
    @CallSuper
    public void onActivityResumed(Activity activity) {
        LogUtil.i(activity.toString()," onActivityResumed .. ");

    }

    @Override
    @CallSuper
    public void onActivityPaused(Activity activity) {
        LogUtil.i(activity.toString()," onActivityPaused .. ");
    }

    @Override
    @CallSuper
    public void onActivityStopped(Activity activity) {

    }

    @Override
    @CallSuper
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    @CallSuper
    public void onActivityDestroyed(Activity activity) {
        LogUtil.i(activity.toString()," onActivityDestroyed .. ActivityList Remove this Activity ");
        am.removeActivity(activity);
    }



}
