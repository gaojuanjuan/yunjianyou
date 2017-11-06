package com.yunjy.jianyou.base;

/**
 * Created by zt
 */

public interface IActivity {

    /**
     * 是否用到了 fragmnet
     * @return
     */
    boolean userFragment();

    int getViewID();

    void initdata();





}
