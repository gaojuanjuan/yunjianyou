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
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.LazyBaseFragment;
import com.yunjy.jianyou.base.PageChiledFragment;
import com.yunjy.jianyou.net.URLManager;
import com.yunjy.jianyou.net.entity.NetOrderList;
import com.yunjy.jianyou.net.entity.OPButtonInfo;
import com.yunjy.jianyou.net.entity.OPButtonNet;
import com.yunjy.jianyou.net.entity.OrderInfo;
import com.yunjy.jianyou.page.fragment.homepage.HomeOpButtonFragment;
import com.yunjy.jianyou.tools.LogUtil;

import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zt on 2017/10/20.
 */

public class OrderAllFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "OrderAllFragment";

    public int orderStatus;

    public OrderAllFragment(int orderStatus) {
        this.orderStatus = orderStatus;
    }

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

    RecyclerView.Adapter madapter = new RecyclerView.Adapter<ItemOrderHolder>() {
        @Override
        public ItemOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ItemOrderHolder(View.inflate(parent.getContext(), R.layout.item_order_layout, null));
        }

        @Override
        public void onBindViewHolder(ItemOrderHolder holder, int position) {
            holder.bindData(orderList.get(position));
        }

        @Override
        public int getItemCount() {
            return orderList.size();
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

    ArrayList<OrderInfo> orderList = new ArrayList<>();

    @Override
    public void onRefresh() {
        order_swipe_refresh.setRefreshing(true);

        OkGo.getInstance().post(URLManager.getUrl(URLManager.get_order))
                .params("type", orderStatus)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtil.i(TAG, "onSuccess() called with: s = [ " + s + " ]");
                        order_swipe_refresh.setRefreshing(false);
                        try {
                            NetOrderList baseEntity = JSON.parseObject(s, NetOrderList.class);
                            ArrayList<OrderInfo> data = baseEntity.getData();
                            orderList.clear();
                            orderList.addAll(data);
                            madapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        order_swipe_refresh.setRefreshing(false);
                        e.printStackTrace();
                    }
                });


    }


    class ItemOrderHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView order_status, order_title, order_detail_info, order_price, shop_address;
        public ImageView order_pic;

        public ItemOrderHolder(View itemView) {
            super(itemView);

            order_status = itemView.findViewById(R.id.order_status);
            order_title = itemView.findViewById(R.id.order_title);
            order_detail_info = itemView.findViewById(R.id.order_detail_info);
            order_price = itemView.findViewById(R.id.order_price);
            shop_address = itemView.findViewById(R.id.shop_address);
            order_pic = itemView.findViewById(R.id.order_pic);

        }


        public void bindData(OrderInfo mOrderInfo) {

            order_status.setText(OrderStatusEnum.getDescribe(mOrderInfo.getOrderStatus()));

            image(URLManager.getUrl(mOrderInfo.getOrderImg()), order_pic);

            order_title.setText(mOrderInfo.getOrderTitle());
            order_detail_info.setText(mOrderInfo.getOrderDescribe());
            order_price.setText(mOrderInfo.getOrderPrice());
            shop_address.setText(mOrderInfo.getShopAddress());

            itemView.setTag(mOrderInfo);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            OrderInfo mOrderInfo = (OrderInfo) v.getTag();
            //todo
        }
    }


}
