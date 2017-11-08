package com.yunjy.jianyou.page.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return 0;
    }

    @Override
    public void initdata() {

    }
}
