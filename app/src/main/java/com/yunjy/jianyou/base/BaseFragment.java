package com.yunjy.jianyou.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.tools.LogUtil;

/**
 * Created by zt on 2017/9/18.
 */

public class BaseFragment extends Fragment {

    private static final String TAG = "jianyou_BaseFragment";


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i(TAG, getClass().getSimpleName() + " setUserVisibleHint() called with: isVisibleToUser = [" + isVisibleToUser + "]");
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            isVisibleToUser();
        }

    }

    public void isVisibleToUser() {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        LogUtil.i(TAG, getClass().getSimpleName() + " onHiddenChanged() called with: hidden = [" + hidden + "]");
        super.onHiddenChanged(hidden);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(TAG, getClass().getSimpleName() +  "onStart() called");
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, getClass().getSimpleName() +  "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG, getClass().getSimpleName() + "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG, getClass().getSimpleName() +  "onStop() called");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i(TAG, getClass().getSimpleName() +  "onDestroyView() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, getClass().getSimpleName() +  "onDestroy() called");
    }


}
