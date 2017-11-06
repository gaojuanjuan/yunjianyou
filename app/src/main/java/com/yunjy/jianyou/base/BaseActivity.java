package com.yunjy.jianyou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zt on 2017/9/18.
 */

public abstract  class BaseActivity extends AppCompatActivity  implements IActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewID());
        initdata();
    }



}
