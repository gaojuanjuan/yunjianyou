package com.yunjy.jianyou.page.act;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;

/**
 * Created by zt on 2017/9/18.
 */

public class SplashActivity extends BaseActivity {

    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return R.layout.act_splash;
    }

    @Override
    public void initdata() {
        handler.sendEmptyMessageDelayed(1000, 1*1000);
    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };



}
