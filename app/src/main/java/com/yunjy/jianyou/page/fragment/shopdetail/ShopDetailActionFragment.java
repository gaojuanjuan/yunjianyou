package com.yunjy.jianyou.page.fragment.shopdetail;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.LazyBaseFragment;

import java.util.Random;

/**
 * Created by zt on 2017/10/24.
 */

public class ShopDetailActionFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout nearby_shop_swipe_refresh;
    RecyclerView nearby_shop_recycler_view;
    int size = 3;
    RecyclerView.Adapter mAdapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new NearbyShopItem(View.inflate(parent.getContext(), R.layout.item_nearby_shop, null));
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
        View iview = inflater.inflate(R.layout.fragment_nearby_shop, null);


        nearby_shop_swipe_refresh = (SwipeRefreshLayout) iview.findViewById(R.id.nearby_shop_swipe_refresh);
        nearby_shop_recycler_view = (RecyclerView) iview.findViewById(R.id.nearby_shop_recycler_view);

        nearby_shop_swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.textColorPrimary));
        nearby_shop_swipe_refresh.setColorSchemeColors(Color.WHITE);
        nearby_shop_swipe_refresh.setOnRefreshListener(this);

        nearby_shop_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        nearby_shop_recycler_view.setAdapter(mAdapter);

        return iview;
    }

    @Override
    public void loadData() {
        onRefresh();
    }


    @Override
    public void onRefresh() {
        if (nearby_shop_swipe_refresh != null) {
            nearby_shop_swipe_refresh.setRefreshing(true);

            //// TODO: 2017/10/24

            Random random = new Random();

            size = random.nextInt(13);

            mAdapter.notifyDataSetChanged();

            nearby_shop_swipe_refresh.setRefreshing(false);

        }
    }


    class NearbyShopItem extends RecyclerView.ViewHolder {

        public NearbyShopItem(View itemView) {
            super(itemView);
        }


    }
}
