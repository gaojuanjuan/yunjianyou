package com.yunjy.jianyou.page.fragment.feedback;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunjy.jianyou.R;
import com.yunjy.jianyou.base.BaseFragment;
import com.yunjy.jianyou.base.LazyBaseFragment;

/**
 * Created by zt on 2017/10/26.
 */

public class ComplainFragment extends LazyBaseFragment {
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View iview =  inflater.inflate(R.layout.fragment_complain,null);
        return iview;
    }

    @Override
    public void loadData() {

    }
}
