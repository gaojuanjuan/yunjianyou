package com.yunjy.jianyou.page.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;

public class LoginActivity
        extends BaseActivity
        implements View.OnClickListener {

    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return R.layout.activity_login;
    }

    EditText phone, smsCode;
    TextView get_smsCode_;

    @Override
    public void init() {
        findViewById(R.id.login_).setOnClickListener(this);
        findViewById(R.id.register_).setOnClickListener(this);
        findViewById(R.id.login_ll_).setOnClickListener(this);
        get_smsCode_ = (TextView) findViewById(R.id.get_smsCode_);
        get_smsCode_.setOnClickListener(this);
        phone = (EditText) findViewById(R.id.phone);
        smsCode = (EditText) findViewById(R.id.smsCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_smsCode_://点击获取验证码

                break;

            case R.id.login_:

                break;

            case R.id.register_:
                RegisterActivity.jump(this);
                break;
            case R.id.login_ll_://使用账号密码登录

                break;
        }
    }
}
