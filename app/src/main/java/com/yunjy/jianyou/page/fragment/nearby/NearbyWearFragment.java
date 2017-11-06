package com.yunjy.jianyou.page.fragment.nearby;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseFragment;
import com.yunjy.jianyou.base.LazyBaseFragment;

import java.util.Random;

/**
 * Created by zt on 2017/10/24.
 */

public class NearbyWearFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout nearby_wear_swipe_refresh;
    RecyclerView nearby_wear_recycler_view;
    int size = 3;
    RecyclerView.Adapter mAdapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == 2)
                return new NearbyWearItem(View.inflate(parent.getContext(), R.layout.item_nearby_wear_style2, null));
            else
                return new NearbyWearItem(View.inflate(parent.getContext(), R.layout.item_nearby_wear_style1, null));
        }


        @Override
        public int getItemViewType(int position) {
            int type = 1;

            if (position % 2 == 0) {
                type = 2;
            }


            return type;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return size;
        }
    };


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View iview = inflater.inflate(R.layout.fragment_nearby_wear, null);


        nearby_wear_swipe_refresh = (SwipeRefreshLayout) iview.findViewById(R.id.nearby_wear_swipe_refresh);
        nearby_wear_recycler_view = (RecyclerView) iview.findViewById(R.id.nearby_wear_recycler_view);

        nearby_wear_swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.textColorPrimary));
        nearby_wear_swipe_refresh.setColorSchemeColors(Color.WHITE);
        nearby_wear_swipe_refresh.setOnRefreshListener(this);

        nearby_wear_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        nearby_wear_recycler_view.setAdapter(mAdapter);

        return iview;
    }

    @Override
    public void loadData() {
        onRefresh();
    }


    @Override
    public void onRefresh() {
        if (nearby_wear_swipe_refresh != null) {
            nearby_wear_swipe_refresh.setRefreshing(true);

            Random random = new Random();

            size = random.nextInt(13);

            mAdapter.notifyDataSetChanged();
            //// TODO: 2017/10/24

            nearby_wear_swipe_refresh.setRefreshing(false);

        }
    }


    class NearbyWearItem extends RecyclerView.ViewHolder {

        public NearbyWearItem(View itemView) {
            super(itemView);
        }


    }
}
