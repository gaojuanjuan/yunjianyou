package com.yunjy.jianyou.page.act;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;
import com.yunjy.jianyou.net.NetCode;
import com.yunjy.jianyou.net.URLManager;
import com.yunjy.jianyou.net.entity.BaseEntity;
import com.yunjy.jianyou.net.entity.LoginEntity;
import com.yunjy.jianyou.tools.AesUtils;
import com.yunjy.jianyou.tools.KeyUtils;
import com.yunjy.jianyou.tools.LogUtil;
import com.yunjy.jianyou.tools.PhoneInfoUtils;
import com.yunjy.jianyou.tools.SPUtils;
import com.yunjy.jianyou.tools.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private CountDownTimer timer;
    private String phoneNumber;
    private TextView mLogin_btn;
    private boolean hasSend;
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
        mLogin_btn = (TextView) findViewById(R.id.login_);
        mLogin_btn.setOnClickListener(this);
        findViewById(R.id.register_).setOnClickListener(this);
        findViewById(R.id.login_ll_).setOnClickListener(this);
        get_smsCode_ = (TextView) findViewById(R.id.get_smsCode_);
        get_smsCode_.setOnClickListener(this);
        phone = (EditText) findViewById(R.id.phone);
        smsCode = (EditText) findViewById(R.id.smsCode);
        findViewById(R.id.iv_clear).setOnClickListener(this);
        createCountDownTime();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_smsCode_://点击获取验证码
                if (!hasSend){//已经正在发送
                    if (phone.getText() !=null && !TextUtils.isEmpty(phone.getText().toString())){
                        phoneNumber = phone.getText().toString();
                        if (PhoneInfoUtils.isMobileNO(phoneNumber)){
                            timer.start();//开始倒计时
                            hasSend = true;
                            getSmsCode();//向服务器请求发送验证码
                        }else{
                            ToastUtils.showToast(this,getString(R.string.input_correct_phone));
                        }
                    }else{
                        ToastUtils.showToast(this,getString(R.string.input_phone));
                    }
                }
                break;
            case R.id.login_:
                if (hasSend && !TextUtils.isEmpty(smsCode.getText().toString())){//如果验证码已经发送，就可以点击登陆了
                    login();
                }
                break;
            case R.id.register_:
                RegisterActivity.jump(this);
                break;
            case R.id.login_ll_://使用账号密码登录
                PwdLoginActivity.jump(this);
                break;
            case R.id.iv_clear:
                phone.setText("");
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
                        BaseEntity baseEntity = JSON.parseObject(s, BaseEntity.class);
                        if (baseEntity != null){
                            switch (baseEntity.getCode()){
                                case 200:
                                    ToastUtils.showToast(LoginActivity.this,"验证码已发送");
                                    mLogin_btn.setClickable(true);
                                    break;
                                case 15:
                                    againObtainCode();
                                    break;
                            }
                        }else {
                            againObtainCode();
                        }

                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LogUtil.e(TAG,"onError()--getSmsCode_url---Exception:"+e+",Response:"+response.message()+",code:"+response.code());
                        e.printStackTrace();
                    }
                });
    }

    private void againObtainCode() {
        get_smsCode_.setText(R.string.again_obtain);
        ToastUtils.showToast(LoginActivity.this,"验证码发送失败，请重新发送");
        timer.cancel();
        hasSend = false;
    }

    private void createCountDownTime(){
        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                get_smsCode_.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {
                hasSend = false;
                get_smsCode_.setText(R.string.again_obtain);
                smsCode.setText("");
                mLogin_btn.setClickable(false);
            }
        };
    }

    private void encrypt(String pwd) {
        LogUtil.e(TAG, "AES加密前json数据 ---->" + pwd);
        LogUtil.e(TAG, "AES加密前json数据长度 ---->" + pwd.length());
        //生成一个动态key
        String secretKey = AesUtils.createKey(pwd);
        //AES加密
        String encryStr = AesUtils.encrypt(secretKey,pwd);
        LogUtil.e(TAG, "AES加密后json数据 ---->" + encryStr);
        LogUtil.e(TAG, "AES加密后json数据长度 ---->" + encryStr.length());

        //AES解密
        String decryStr = AesUtils.decrypt(secretKey, encryStr);
        LogUtil.e(TAG, "AES解密后json数据 ---->" + decryStr);
    }

    private void login() {
        OkGo.getInstance().post(URLManager.getUrl(URLManager.login_url))
                .params("UserName", phoneNumber)
                .params("ClientType",1)
                .params("LoginType",2)//1.账号密码 ，2.短信验证码，3.第三方登录接口
                .params("Model", PhoneInfoUtils.getModel())
                .params("MAC",PhoneInfoUtils.getMac())
                .params("yzm",smsCode.getText().toString())
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtil.i(TAG, "onSuccess() called with: s = [ " + s + " ]");
                        LoginEntity loginEntity = JSON.parseObject(s, LoginEntity.class);
                        switch (loginEntity.getCode()){
                            case 200:
                                SPUtils.put(LoginActivity.this, KeyUtils.TOKEN,loginEntity.getToken());
                                SPUtils.put(LoginActivity.this, KeyUtils.INTERVALTIME,loginEntity.getIntervalTime());
                                SPUtils.put(LoginActivity.this, KeyUtils.USER_ID,loginEntity.getUser_ID());
                                timer.cancel();//取消倒计时
                                MainActivity.jump(LoginActivity.this);
                                finish();
                                break;
                            case 3://验证码错误
                                ToastUtils.showToast(LoginActivity.this,"验证码错误");
                                break;
                            case 4:case 2:case 1://登陆失败
                                ToastUtils.showToast(LoginActivity.this,"登陆失败，请重试");
                                break;

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LogUtil.e(TAG,"onError()--login_url---Exception:"+e+",Response:"+response.message()+",code:"+response.code());
                        ToastUtils.showToast(LoginActivity.this,"网络异常请重试");
                        e.printStackTrace();
                    }
                });
    }

    public static void jump(Activity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
