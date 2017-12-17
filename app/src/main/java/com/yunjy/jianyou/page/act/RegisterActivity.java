package com.yunjy.jianyou.page.act;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;
import com.yunjy.jianyou.net.URLManager;
import com.yunjy.jianyou.tools.LogUtil;
import com.yunjy.jianyou.tools.PhoneInfoUtils;
import com.yunjy.jianyou.tools.ToastUtils;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = RegisterActivity.class.getSimpleName();
    private View mTvNext;
    private boolean hasSend;
    private EditText mEtPhone;
    private String phoneNumber;
    private CountDownTimer timer;
    private TextView mTvGetCode;
    private EditText mEtCode;

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
        findViewById(R.id.back).setOnClickListener(this);
        mTvNext = findViewById(R.id.tv_next);
        mTvNext.setOnClickListener(this);
        mTvGetCode = ((TextView) findViewById(R.id.code_get_));
        mTvGetCode.setOnClickListener(this);
        mEtPhone = ((EditText) findViewById(R.id.et_phone));
        mEtCode = ((EditText) findViewById(R.id.et_code));
        createCountDownTime();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next://点击下一步,是否需要请求接口验证填写的验证码填写的是否正确
                if (hasSend && !TextUtils.isEmpty(mEtCode.getText().toString())){//如果验证码已经发送并填写好，就可以点击进入下一步了
                    timer.cancel();//取消倒计时
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.code_get_:
                if (!hasSend){//已经正在发送
                    if (mEtPhone.getText() !=null && !TextUtils.isEmpty(mEtPhone.getText().toString())){
                        phoneNumber = mEtPhone.getText().toString();
                        if (PhoneInfoUtils.isMobileNO(phoneNumber)){
                            timer.start();//开始倒计时
                            hasSend = true;
                            getSmsCode();//向服务器请求发送验证码
                        }else{
                            ToastUtils.showToast(this,"请输入正确的手机号");
                        }
                    }else{
                        ToastUtils.showToast(this,"请输入手机号");
                    }
                }
                break;
        }
    }


    private void getSmsCode() {
        OkGo.getInstance().post(URLManager.getUrl(URLManager.getSmsCode_url))
                .params("recnum", phoneNumber)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtil.i(TAG, "onSuccess() called with: s = [ " + s + " ]");
                        ToastUtils.showToast(RegisterActivity.this,"验证码已发送");
                        mTvNext.setClickable(true);
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LogUtil.e(TAG,"onError:"+e);
                        e.printStackTrace();
                    }
                });
    }


    private void createCountDownTime(){
        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvGetCode.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {
                hasSend = false;
                mTvGetCode.setText("重新获取");
                mEtCode.setText("");
                mTvNext.setClickable(false);
            }
        };
    }
}
