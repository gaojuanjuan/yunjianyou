package com.yunjy.jianyou.page.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.page.fragment.HomeFragment;
import com.yunjy.jianyou.page.fragment.MeFragment;
import com.yunjy.jianyou.page.fragment.NearbyFragment;
import com.yunjy.jianyou.page.fragment.OrderFragment;
import com.yunjy.jianyou.tools.KeyUtils;
import com.yunjy.jianyou.tools.SPUtils;

import org.apache.log4j.chainsaw.Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity
        extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, AMapLocationListener {


    private static final String TAG = "MainActivity";


    ViewPager main_vp;
    RadioGroup bnv;

    HomeFragment homeFragment;
    NearbyFragment nearbyFragment;
    OrderFragment orderFragment;
    MeFragment meFragment;

    private List<Fragment> mTabs = new ArrayList<Fragment>();


    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    public static void jump(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object token = SPUtils.get(MainActivity.this, KeyUtils.TOKEN, "");
        if (token ==null || TextUtils.isEmpty(token.toString())){
            LoginActivity.jump(MainActivity.this);
            finish();
            return;
        }

        main_vp = (ViewPager) findViewById(R.id.main_vp);
        bnv = (RadioGroup) findViewById(R.id.bnv);


        homeFragment = new HomeFragment();
        nearbyFragment = new NearbyFragment();
        orderFragment = new OrderFragment();
        meFragment = new MeFragment();

        mTabs.add(homeFragment);
        mTabs.add(nearbyFragment);
        mTabs.add(orderFragment);
        mTabs.add(meFragment);
        main_vp.setOffscreenPageLimit(5);

        FragmentPagerAdapter mViewPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {

                return mTabs.get(arg0);
            }
        };

        main_vp.setAdapter(mViewPagerAdapter);
        main_vp.addOnPageChangeListener(this);
        bnv.setOnCheckedChangeListener(this);
        bnv.check(R.id.home_rb);

        addLocationListener(homeFragment);
        addLocationListener(nearbyFragment);

        initLocation();
    }

    public void initLocation() {

        mlocationClient = new AMapLocationClient(this);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mlocationClient.setLocationOption(mLocationOption);

        mlocationClient.startLocation();
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        callLocationListener(amapLocation);
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }


    }

    ArrayList<OnLocationListener> onLocationListeners = new ArrayList<>();

    public void addLocationListener(OnLocationListener mOnLocationListener) {
        onLocationListeners.add(mOnLocationListener);
    }

    public void removeLocationListener(OnLocationListener mOnLocationListener) {
        onLocationListeners.remove(mOnLocationListener);
    }

    private void callLocationListener(AMapLocation amapLocation) {
        for (OnLocationListener mOnLocationListener : onLocationListeners) {
            mOnLocationListener.onLocationChanged(amapLocation);
        }
    }

    public interface OnLocationListener {

        public void onLocationChanged(AMapLocation amapLocation);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.me_rb:
                main_vp.setCurrentItem(3);
                break;
            case R.id.home_rb:
                main_vp.setCurrentItem(0);
                break;
            case R.id.order_rb:
                main_vp.setCurrentItem(2);
                break;
            case R.id.nearby_rb:
                main_vp.setCurrentItem(1);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                bnv.check(R.id.home_rb);
                break;
            case 1:
                bnv.check(R.id.nearby_rb);
                break;
            case 2:
                bnv.check(R.id.order_rb);
                break;
            case 3:
                bnv.check(R.id.me_rb);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
