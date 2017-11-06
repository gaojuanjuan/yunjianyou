package com.yunjy.jianyou.page;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.yunjy.jianyou.R;
import java.util.ArrayList;
import java.util.Set;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: requestCode "+requestCode+" resultCode "+resultCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
