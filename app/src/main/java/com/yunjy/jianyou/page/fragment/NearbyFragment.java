package com.yunjy.jianyou.page.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyHotFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyShopFragment;
import com.yunjy.jianyou.page.fragment.nearby.NearbyWearFragment;

import java.util.ArrayList;

/**
 * Created by zt on 2017/10/18.
 */

public class NearbyFragment extends BaseFragment {

    ArrayList<Fragment> fragments = new ArrayList<>();

    ArrayList<String> names = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View iview = inflater.inflate(R.layout.fragment_nearby_layout, null);

        ViewPager nearby_vp = iview.findViewById(R.id.nearby_vp);

        TabLayout nearby_tab = iview.findViewById(R.id.nearby_tab);


        nearby_tab.setTabMode(TabLayout.MODE_FIXED);
        fragments.clear();
        names.clear();


        names.add("热门");
        names.add("店铺");
        names.add("服装");
        fragments.add(new NearbyHotFragment());
        fragments.add(new NearbyShopFragment());
        fragments.add(new NearbyWearFragment());

        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
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

        nearby_vp.setAdapter(mFragmentPagerAdapter);
        nearby_tab.setupWithViewPager(nearby_vp);


        return iview;
    }


}
