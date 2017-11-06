package com.yunjy.jianyou.page.act;

import android.view.View;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;

/**
 * Created by zt on 2017/10/24.
 */

public class CommodityDetailAct extends BaseActivity {


    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return R.layout.act_commodity_detail;
    }

    @Override
    public void initdata() {

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
