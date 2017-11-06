package com.yunjy.jianyou;

import android.content.Context;

import com.yunjy.jianyou.db.DaoHelper;


/**
 * Created by zt on 2017/9/18.
 */

public class ConfigGlobal {

    public static final String GLOBALTAG = "SayInfo - speaker_v2 ";

    public static String DBNAME = "rns_common";
    public static int DBVERSION = 1;
    public static DaoHelper mDaoHelper;


    public final  static boolean is_Debug = true;
    public static String pack_Name = "";//

    public static boolean isDebug(){
        return is_Debug;
    }
    public ConfigGlobal(Context context) {
        if (context == null) {
            throw new RuntimeException("APPContext  is null !");
        }
        mDaoHelper = new DaoHelper(context, DBNAME, DBVERSION);
    }


}
