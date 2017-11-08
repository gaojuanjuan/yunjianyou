package com.yunjy.jianyou.base;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.lzy.imagepicker.loader.ImageLoader;
import com.yunjy.jianyou.R;

import java.io.File;

/**
 * Created by Administrator on 2017/3/20.
 */

public class GlideImagePickerLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        Glide.with(activity)//
                .load(new File(path))//
                .placeholder(R.drawable.logo_grey)//
                .error(R.drawable.logo_grey)//
                .into(imageView);

    }

    @Override
    public void clearMemoryCache() {
    }



}
