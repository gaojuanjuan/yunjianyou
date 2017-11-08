package com.yunjy.jianyou.page.act;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseActivity;
import com.yunjy.jianyou.page.fragment.nearby.NearbyHotFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyShopFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyWearFragment;
import com.yunjy.jianyou.page.fragment.shopdetail.ShopDetailActionFragment;
import com.yunjy.jianyou.page.fragment.shopdetail.ShopDetailHomeFragment;

import java.util.ArrayList;

public class ShopDetailActivity extends BaseActivity {



    ArrayList<Fragment> fragments = new ArrayList<>();

    ArrayList<String> names = new ArrayList<>();

    @Override
    public boolean userFragment() {
        return true;
    }

    @Override
    public int getViewID() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public void initdata() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabLayout shop_detail_tab = (TabLayout)findViewById(R.id.shop_detail_tab);
        ViewPager shop_detail_vp = (ViewPager)findViewById(R.id.shop_detail_vp);

        shop_detail_tab.setTabMode(TabLayout.MODE_FIXED);
        fragments.clear();
        names.clear();

        names.add("首页");
        names.add("新品");
        names.add("活动");

        fragments.add(new ShopDetailHomeFragment());
        fragments.add(new ShopDetailHomeFragment());
        fragments.add(new ShopDetailActionFragment());

        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return names.get(position);
            }
        };

        shop_detail_vp.setAdapter(mFragmentPagerAdapter);
        shop_detail_tab.setupWithViewPager(shop_detail_vp);



    }
}
