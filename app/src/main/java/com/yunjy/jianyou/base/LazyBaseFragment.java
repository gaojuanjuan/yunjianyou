package com.yunjy.jianyou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.net.URLManager;
import com.yunjy.jianyou.page.fragment.order.OrderStatusEnum;

/**
 * Created by zt on 2017/10/25.
 */

public abstract class LazyBaseFragment extends Fragment {

    boolean isViewPrepared = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = initView(inflater, container);
        if (container != null) {
            container.removeView(view);
        }
        isViewPrepared = true;
        return view;
    }

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isViewPrepared) {
            loadData();
        }
    }

    public abstract void loadData();

    @Override
    public void onDestroyView() {
        isViewPrepared = false;
        super.onDestroyView();

    }

    public void image(String url, ImageView iv){

        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.logo_grey)
                .error(R.drawable.logo_grey)
                .into(iv);

    }


}
