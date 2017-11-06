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
import com.yunjy.jianyou.page.fragment.collection.CollectionCommodityFragment;
import com.yunjy.jianyou.page.fragment.collection.CollectionShopFragment;
import com.yunjy.jianyou.page.fragment.feedback.ComplainFragment;
import com.yunjy.jianyou.page.fragment.feedback.FeedbackFragment;

import java.util.ArrayList;

public class CollectionAct extends BaseActivity {

    ArrayList<Fragment> fragments = new ArrayList<>();

    ArrayList<String> names = new ArrayList<>();

    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return R.layout.activity_collection;
    }

    @Override
    public void initdata() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewPager collection_vp = (ViewPager)findViewById(R.id.collection_vp);

        TabLayout collection_tablayout = (TabLayout)findViewById(R.id.collection_tablayout);

        collection_tablayout.setTabMode(TabLayout.MODE_FIXED);

        fragments.clear();
        names.clear();

        names.add("商品");
        names.add("店铺");

        fragments.add(new CollectionCommodityFragment());
        fragments.add(new CollectionShopFragment());

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

        collection_vp.setAdapter(mFragmentPagerAdapter);
        collection_tablayout.setupWithViewPager(collection_vp);
    }
}
