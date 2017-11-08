package com.yunjy.jianyou.page.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseFragment;
import com.yunjy.jianyou.page.act.CollectionAct;
import com.yunjy.jianyou.page.act.FeedbackAct;
import com.yunjy.jianyou.page.act.ShopDetailActivity;

/**
 * Created by zt on 2017/10/18.
 */

public class MeFragment extends BaseFragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View iview  =  inflater.inflate(R.layout.fragment_me_layout,null);
        iview.findViewById(R.id.feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeedbackAct.class));
            }
        });
        iview.findViewById(R.id.collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CollectionAct.class));
            }
        });

        iview.findViewById(R.id.feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShopDetailActivity.class));
            }
        });



        return iview;
    }

}
