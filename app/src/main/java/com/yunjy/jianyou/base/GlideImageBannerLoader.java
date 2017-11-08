package com.yunjy.jianyou.base;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.youth.banner.loader.ImageLoader;
import com.yunjy.jianyou.R;

import java.io.File;

/**
 * Created by Administrator on 2017/3/20.
 */

public class GlideImageBannerLoader extends ImageLoader {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)//
                .load(path)//
                .placeholder(R.drawable.logo_grey)//
                .error(R.drawable.logo_grey)//
                .into(imageView);

    }
}
