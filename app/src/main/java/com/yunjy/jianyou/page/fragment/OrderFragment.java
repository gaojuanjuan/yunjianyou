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
import android.widget.TableLayout;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseFragment;
import com.yunjy.jianyou.page.fragment.order.OrderAllFragment;
import com.yunjy.jianyou.page.fragment.order.OrderStatusEnum;

import java.util.ArrayList;

/**
 * Created by zt on 2017/10/18.
 */

public class OrderFragment extends BaseFragment {

    TabLayout order_tab;
    ViewPager order_vp;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> orderStatus = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View order_layout = inflater.inflate(R.layout.fragment_order_layout, null);
        order_tab = (TabLayout) order_layout.findViewById(R.id.order_tab);
        order_vp = (ViewPager) order_layout.findViewById(R.id.order_vp);
        fragments.clear();
        orderStatus.clear();
        orderStatus.add("全部");
        orderStatus.add("待取货");
        orderStatus.add("已取货");
        orderStatus.add("待评价");
        orderStatus.add("退款/售后");
        fragments.add(new OrderAllFragment(OrderStatusEnum.all.orderStatus));
        fragments.add(new OrderAllFragment(OrderStatusEnum.unreceive.orderStatus));
        fragments.add(new OrderAllFragment(OrderStatusEnum.receive.orderStatus));
        fragments.add(new OrderAllFragment(OrderStatusEnum.evaluate.orderStatus));
        fragments.add(new OrderAllFragment(OrderStatusEnum.afterSale.orderStatus));
        order_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
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
                return orderStatus.get(position);
            }
        };

        order_vp.setAdapter(mFragmentPagerAdapter);
        order_tab.setupWithViewPager(order_vp);
        return order_layout;
    }


}
