package com.yunjy.jianyou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zt on 2017/10/25.
 */

public abstract class LazyBaseFragment extends Fragment {

    boolean isViewPrepared = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = initView(inflater, container);
        if (container != null) {
            container.removeView(view);
        }
        isViewPrepared = true;
        return view;
    }

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isViewPrepared) {
            loadData();
        }
    }

    public abstract void loadData();

    @Override
    public void onDestroyView() {
        isViewPrepared = false;
        super.onDestroyView();

    }
}
