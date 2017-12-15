package com.yunjy.jianyou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.yunjy.jianyou.R;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zt on 2017/11/8.
 */

public abstract class PageChiledFragment extends BaseFragment {


    boolean isViewPrepared = false;
    private RelativeLayout content_rl_;
    private TextView err_tv_;
    private RelativeLayout load_rl_;
    ViewGroup mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initBaseView(inflater, container);
        content_rl_.addView(initView(inflater, container));
        isViewPrepared = true;
        return mRootView;
    }

    public View initBaseView(LayoutInflater inflater, @Nullable ViewGroup container) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_base_layout, container);

        err_tv_ = (TextView) mRootView.findViewById(R.id.err_tv_);
        content_rl_ = (RelativeLayout) mRootView.findViewById(R.id.content_rl_);
        load_rl_ = (RelativeLayout) mRootView.findViewById(R.id.load_rl_);

        err_tv_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reLoad();
            }
        });


        return mRootView;
    }


    public void reLoad(){

    }



    public abstract class  mPageChiledFragmetNetCallback extends StringCallback{


        @Override
        public void onBefore(BaseRequest request) {
            super.onBefore(request);
            showLoading();
        }


        @Override
        public void onError(Call call, Response response, Exception e) {
            super.onError(call, response, e);
            e.printStackTrace();
            showErr();
        }



    }


    public void addContentView(View view) {

        if (mRootView != null) {
            mRootView.removeView(view);
            mRootView.addView(view);
        }
    }


    public void removeView(View view) {
        if (mRootView != null) {
            mRootView.removeView(view);
        }
    }


    public void showLoading() {

        if (err_tv_.getVisibility() == View.VISIBLE) {
            err_tv_.setVisibility(View.GONE);
        }
        if (content_rl_.getVisibility() == View.VISIBLE) {
            content_rl_.setVisibility(View.GONE);
        }
        if (load_rl_.getVisibility() != View.VISIBLE) {
            load_rl_.setVisibility(View.VISIBLE);
        }
    }





    public void showErr() {
        if (err_tv_.getVisibility() != View.VISIBLE) {
            err_tv_.setVisibility(View.VISIBLE);
        }
        if (content_rl_.getVisibility() == View.VISIBLE) {
            content_rl_.setVisibility(View.GONE);
        }
        if (load_rl_.getVisibility() == View.VISIBLE) {
            load_rl_.setVisibility(View.GONE);
        }
    }


    public void showContentView() {

        if (err_tv_.getVisibility() == View.VISIBLE) {
            err_tv_.setVisibility(View.GONE);
        }
        if (content_rl_.getVisibility() != View.VISIBLE) {
            content_rl_.setVisibility(View.VISIBLE);
        }
        if (load_rl_.getVisibility() == View.VISIBLE) {
            load_rl_.setVisibility(View.GONE);
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }


    public abstract void loadData();

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isViewPrepared) {
            userVisibleLoadData();
        }
    }

    public abstract void userVisibleLoadData();


    @Override
    public void onDestroyView() {
        isViewPrepared = false;
        super.onDestroyView();

    }


}
