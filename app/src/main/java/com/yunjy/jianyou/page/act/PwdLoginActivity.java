package com.yunjy.jianyou.page.act;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;

public class PwdLoginActivity extends BaseActivity {

    public static void jump(Activity activity){
        Intent intent = new Intent(activity, PwdLoginActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return R.layout.activity_pwd_login;
    }

    @Override
    public void init() {

    }
}
