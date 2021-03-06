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
import com.yunjy.jianyou.page.fragment.feedback.ComplainFragment;
import com.yunjy.jianyou.page.fragment.feedback.FeedbackFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyHotFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyShopFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyWearFragment;

import java.util.ArrayList;

public class FeedbackAct extends BaseActivity {


    ArrayList<Fragment> fragments = new ArrayList<>();

    ArrayList<String> names = new ArrayList<>();

    @Override
    public boolean userFragment() {
        return false;
    }

    @Override
    public int getViewID() {
        return R.layout.activity_feedback;
    }

    @Override
    public void init() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ViewPager feedback_vp = (ViewPager)findViewById(R.id.feedback_vp);

        TabLayout feedback_tablayout = (TabLayout)findViewById(R.id.feedback_tablayout);

        feedback_tablayout.setTabMode(TabLayout.MODE_FIXED);

        fragments.clear();
        names.clear();

        names.add("体验问题");
        names.add("商品/商家投诉");

        fragments.add(new FeedbackFragment());
        fragments.add(new ComplainFragment());

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

        feedback_vp.setAdapter(mFragmentPagerAdapter);
        feedback_tablayout.setupWithViewPager(feedback_vp);

    }
}
