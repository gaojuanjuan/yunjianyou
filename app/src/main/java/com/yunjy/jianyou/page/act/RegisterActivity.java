package com.yunjy.jianyou.page.act;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;

public class RegisterActivity extends BaseActivity {


   public static void jump(Activity activity){
       Intent intent = new Intent(activity, RegisterActivity.class);
       activity.startActivity(intent);
   }


    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return R.layout.activity_register;
    }

    @Override
    public void init() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
