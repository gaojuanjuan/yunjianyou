package com.yunjy.jianyou.page.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseFragment;
import com.yunjy.jianyou.page.act.MainActivity;

/**
 * Created by zt on 2017/10/18.
 */

public class HomeFragment
        extends BaseFragment
        implements MainActivity.OnLocationListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_layout,null);
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

    }
}
