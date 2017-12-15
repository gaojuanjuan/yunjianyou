package com.yunjy.jianyou.page.fragment.homepage;

import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.PageChiledFragment;
import com.yunjy.jianyou.net.URLManager;
import com.yunjy.jianyou.net.entity.BannerInfo;
import com.yunjy.jianyou.net.entity.BaseEntity;
import com.yunjy.jianyou.net.entity.OPButtonInfo;
import com.yunjy.jianyou.net.entity.OPButtonNet;
import com.yunjy.jianyou.tools.LogUtil;
import com.yunjy.jianyou.tools.ToastUtils;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zt on 2017/11/8.
 */

public class HomeOpButtonFragment extends PageChiledFragment implements View.OnClickListener {

    private static final String TAG = "HomeOpButtonFrag";



    class ItemOPBUttonHolder implements View.OnClickListener {
        View itemView;
        ImageView op_button_pic;
        TextView op_button_name;

        public ItemOPBUttonHolder(OPButtonInfo mOPButtonInfo) {
            itemView = View.inflate(getContext(), R.layout.item_fragment_home_opbutton, null);

            op_button_pic = (ImageView) itemView.findViewById(R.id.op_button_pic);
            op_button_name = (TextView) itemView.findViewById(R.id.op_button_name);

            Glide.with(getContext())
                    .load(URLManager.getUrl(mOPButtonInfo.imgUrl))
                    .placeholder(R.drawable.logo_grey)
                    .error(R.drawable.logo_grey)
                    .into(op_button_pic);
            op_button_name.setText(mOPButtonInfo.sname);
            itemView.setTag(mOPButtonInfo);
            itemView.setOnClickListener(this);
        }

        public View getView() {
            return itemView;
        }

        @Override
        public void onClick(View v) {
            OPButtonInfo mOPButtonInfo = (OPButtonInfo) v.getTag();
            //todo


        }


    }


    @Override
    public void loadData() {
        LogUtil.i(TAG, "loadData() called");
        opbutton_content_box.removeAllViews();
        showLoading();
        OkGo.getInstance().post(URLManager.getUrl(URLManager.mhome_op_button_url))
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new mPageChiledFragmetNetCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtil.i(TAG, "onSuccess() called with: s = [ " + s + " ]");
                        try {
                            OPButtonNet baseEntity = JSON.parseObject(s, OPButtonNet.class);
                            ArrayList<OPButtonInfo> data = baseEntity.getData();

                            for (OPButtonInfo mOPButtonInfo :
                                    data) {

                                ItemOPBUttonHolder itemOPBUttonHolder = new ItemOPBUttonHolder(mOPButtonInfo);

                                opbutton_content_box.addView(itemOPBUttonHolder.getView());
                            }
                            showContentView();
                        } catch (Exception e) {
                            showErr();
                            e.printStackTrace();
                        }
                    }
                });
    }


    @Override
    public void reLoad() {
        super.reLoad();
        loadData();
    }


    LinearLayout opbutton_content_box;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_home_opbutton_layout, container);

        opbutton_content_box = (LinearLayout) inflate.findViewById(R.id.opbutton_content_box);
        return inflate;
    }

    @Override
    public void userVisibleLoadData() {

    }

    @Override
    public void onClick(View v) {


    }
}
