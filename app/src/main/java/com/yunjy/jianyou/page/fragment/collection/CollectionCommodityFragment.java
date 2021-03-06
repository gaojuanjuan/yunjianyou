package com.yunjy.jianyou.page.fragment.collection;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.LazyBaseFragment;
import com.yunjy.jianyou.tools.LogUtil;

import java.util.Random;

/**
 * Created by zt on 2017/10/26.
 */

public class CollectionCommodityFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "CollectionCommodityFrag";

    SwipeRefreshLayout cc_swipe_refresh;
    RecyclerView cc_list_rv;
    int size = 2;
    RecyclerView.Adapter madapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ItemCollectionCommodityHolder(View.inflate(parent.getContext(), R.layout.item_collection_commodity_layout, null));
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

        View inflate = inflater.inflate(R.layout.fragment_collec_commodity, null);
        if (container != null) {
            container.removeView(inflate);
        }
        cc_swipe_refresh = (SwipeRefreshLayout) inflate.findViewById(R.id.cc_swipe_refresh);
        cc_swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.textColorPrimary));
        cc_swipe_refresh.setColorSchemeColors(Color.WHITE);
        cc_swipe_refresh.setOnRefreshListener(this);
        cc_list_rv = (RecyclerView) inflate.findViewById(R.id.cc_list_rv);

        cc_list_rv.setAdapter(madapter);
        LogUtil.i(TAG, "onCreateView() called  ");
        return inflate;

    }

    @Override
    public void loadData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        cc_swipe_refresh.setRefreshing(true);

        Random random = new Random();
        LogUtil.i(TAG, "---   is onRefresh  ");
        size = random.nextInt(15);
        madapter.notifyDataSetChanged();
        //refresh data

        cc_swipe_refresh.setRefreshing(false);

    }


    class ItemCollectionCommodityHolder extends RecyclerView.ViewHolder {


        public ItemCollectionCommodityHolder(View itemView) {
            super(itemView);

        }
    }


}
