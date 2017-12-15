package com.yunjy.jianyou.net;

/**
 * Created by zt on 2017/11/8.
 */

public class URLManager {


    private static  String mbaseUrl = "http://www.jy1588.cn";


    public static void changeBaseUrl(String baseUrl){
        mbaseUrl = baseUrl;
    }


    public static String getUrl(String childUrl){
        return mbaseUrl + childUrl;
    }


    public static String mBanner_url = "/wx/api/get_photo";
    public static String mhome_op_button_url = "/wx/api/get_submit";

    public static String get_order = "/wx/api/get_order";


}
