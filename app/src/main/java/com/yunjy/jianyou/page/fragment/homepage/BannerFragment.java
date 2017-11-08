package com.yunjy.jianyou.page.fragment.homepage;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.GlideImageBannerLoader;
import com.yunjy.jianyou.base.PageChiledFragment;
import com.yunjy.jianyou.net.URLManager;
import com.yunjy.jianyou.net.entity.BannerInfo;
import com.yunjy.jianyou.net.entity.BaseEntity;
import com.yunjy.jianyou.tools.LogUtil;
import com.yunjy.jianyou.tools.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zt on 2017/11/8.
 */

public class BannerFragment extends PageChiledFragment implements View.OnClickListener {

    private static final String TAG = "BannerFragment";


    @Override
    public void loadData() {
        LogUtil.i(TAG, "loadData() called");

        OkGo.getInstance().post(URLManager.getUrl(URLManager.mBanner_url))
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new mPageChiledFragmetNetCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtil.i(TAG, "onSuccess() called with: s = [ " + s + " ]");
                        try {
                            com.yunjy.jianyou.net.entity.Banner baseEntity = JSON.parseObject(s, com.yunjy.jianyou.net.entity.Banner.class);
                            ArrayList<BannerInfo> data = baseEntity.getData();
                            banner.setImages(data);
                            banner.start();
                            showContentView();
                        } catch (Exception e) {
                            showErr();
                            e.printStackTrace();
                        }
                    }
                });
    }

    Banner banner;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View inflate = inflater.inflate(R.layout.homepage_banner_fag_layout, container);
        banner = inflate.findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {

                BannerInfo mBannerInfo = (BannerInfo) path;
                String img = mBannerInfo.getImg();
                Glide.with(context)//
                        .load(URLManager.getUrl(img))//
                        .placeholder(R.drawable.logo_grey)//
                        .error(R.drawable.logo_grey)//
                        .into(imageView);
                imageView.setTag(mBannerInfo);
                imageView.setOnClickListener(BannerFragment.this);


            }
        });

        return inflate;
    }

    @Override
    public void userVisibleLoadData() {

    }

    @Override
    public void onClick(View v) {
        BannerInfo mBannerInfo = (BannerInfo) v.getTag();
        String img = mBannerInfo.getImg();
        String src = mBannerInfo.getSrc();

        ToastUtils.showToast(getActivity().getApplicationContext(), src);

    }
}
