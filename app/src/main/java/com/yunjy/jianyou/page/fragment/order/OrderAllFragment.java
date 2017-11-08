package com.yunjy.jianyou.page.fragment.order;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
 * Created by zt on 2017/10/20.
 */

public class OrderAllFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "OrderAllFragment";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    SwipeRefreshLayout order_swipe_refresh;
    RecyclerView order_list_rv;
    int size = 2;
    RecyclerView.Adapter madapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ItemOrderHolder(View.inflate(parent.getContext(), R.layout.item_order_layout, null));
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
        View inflate = inflater.inflate(R.layout.fragment_order_all, null);
        if (container != null) {
            container.removeView(inflate);
        }
        order_swipe_refresh = (SwipeRefreshLayout) inflate.findViewById(R.id.order_swipe_refresh);
        order_swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.textColorPrimary));
        order_swipe_refresh.setColorSchemeColors(Color.WHITE);
        order_swipe_refresh.setOnRefreshListener(this);
        order_list_rv = (RecyclerView) inflate.findViewById(R.id.order_list_rv);

        order_list_rv.setAdapter(madapter);
        LogUtil.i(TAG, "onCreateView() called  ");
        return inflate;
    }

    @Override
    public void loadData() {
        onRefresh();
    }


    @Override
    public void onRefresh() {
        order_swipe_refresh.setRefreshing(true);

        Random random = new Random();
        LogUtil.i(TAG, "---   is onRefresh  ");
        size = random.nextInt(15);
        madapter.notifyDataSetChanged();
        //refresh data

        order_swipe_refresh.setRefreshing(false);

    }


    class ItemOrderHolder extends RecyclerView.ViewHolder {


        public ItemOrderHolder(View itemView) {
            super(itemView);

        }
    }


}
