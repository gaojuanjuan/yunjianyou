package com.yunjy.jianyou.integration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.yunjy.jianyou.tools.LogUtils;

/**
 * Created by zt on 2017/9/18.
 */

public class FragmentLifecycle extends FragmentManager.FragmentLifecycleCallbacks {


    @Override
    public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentPreAttached(fm, f, context);
    }

    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentAttached(fm, f, context);
        LogUtils.i(f.toString()," onFragmentAttached .. ");

    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
        LogUtils.i(f.toString()," onFragmentCreated .. ");
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState);
        LogUtils.i(f.toString()," onFragmentActivityCreated .. ");


    }

    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState);
        LogUtils.i(f.toString()," onFragmentViewCreated .. ");
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        super.onFragmentStarted(fm, f);
        LogUtils.i(f.toString()," onFragmentStarted .. ");
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        super.onFragmentResumed(fm, f);

        LogUtils.i(f.toString()," onFragmentResumed .. ");

    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        super.onFragmentPaused(fm, f);
        LogUtils.i(f.toString()," onFragmentPaused .. ");
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        super.onFragmentStopped(fm, f);

        LogUtils.i(f.toString()," onFragmentStopped .. ");

    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        super.onFragmentSaveInstanceState(fm, f, outState);

        LogUtils.i(f.toString()," onFragmentSaveInstanceState .. ");

    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentViewDestroyed(fm, f);
        LogUtils.i(f.toString()," onFragmentViewDestroyed .. ");
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentDestroyed(fm, f);
        LogUtils.i(f.toString()," onFragmentDestroyed .. ");
    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);
    }
}
