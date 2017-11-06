package com.yunjy.jianyou.tools;

import android.os.Environment;


import com.yunjy.jianyou.JianYouApp;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zt
 */

public class StoreFileUtils {

    public static File getStoreDir(){
        File dir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = Environment.getExternalStorageDirectory();
        } else {
            dir = JianYouApp.mContext.getDir("tmp", MODE_PRIVATE);
        }

        return dir;
    }

}
