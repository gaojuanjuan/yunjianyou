package com.yunjy.jianyou.page.fragment.nearby;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseFragment;
import com.yunjy.jianyou.base.LazyBaseFragment;
import com.yunjy.jianyou.page.act.CommodityDetailAct;

import java.util.Random;

/**
 * Created by zt on 2017/10/24.
 */

public class NearbyHotFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout nearby_hot_swipe_refresh;
    RecyclerView nearby_hot_recycler_view;

    int size = 12;

    RecyclerView.Adapter madapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new NearbyHotItem(View.inflate(parent.getContext(), R.layout.item_nearby_hot, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), CommodityDetailAct.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return size;
        }
    };

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {

        View iview = inflater.inflate(R.layout.fragment_hot_nearby_layout, null);

        if (container != null) {
            container.removeView(iview);
        }

        nearby_hot_swipe_refresh = (SwipeRefreshLayout) iview.findViewById(R.id.nearby_hot_swipe_refresh);
        nearby_hot_recycler_view = (RecyclerView) iview.findViewById(R.id.nearby_hot_recycler_view);

        nearby_hot_swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.textColorPrimary));
        nearby_hot_swipe_refresh.setColorSchemeColors(Color.WHITE);
        nearby_hot_swipe_refresh.setOnRefreshListener(this);

        nearby_hot_recycler_view.setLayoutManager(new GridLayoutManager(getContext(), 2));
        nearby_hot_recycler_view.setAdapter(madapter);

        return iview;
    }

    @Override
    public void loadData() {
        onRefresh();
    }


    @Override
    public void onRefresh() {
        if (nearby_hot_swipe_refresh != null) {
            nearby_hot_swipe_refresh.setRefreshing(true);

            Random random = new Random();

            size = random.nextInt(13);

            madapter.notifyDataSetChanged();

            //// TODO: 2017/10/24

            nearby_hot_swipe_refresh.setRefreshing(false);

        }
    }


    class NearbyHotItem extends RecyclerView.ViewHolder {

        public NearbyHotItem(View itemView) {
            super(itemView);
        }


    }
}
