package com.yunjy.jianyou.page.act;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.page.fragment.HomeFragment;
import com.yunjy.jianyou.page.fragment.MeFragment;
import com.yunjy.jianyou.page.fragment.NearbyFragment;
import com.yunjy.jianyou.page.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity
        extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    private static final String TAG = "MainActivity";


    ViewPager main_vp;
    RadioGroup bnv;

    HomeFragment homeFragment;
    NearbyFragment nearbyFragment;
    OrderFragment orderFragment;
    MeFragment meFragment;

    private List<Fragment> mTabs = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
