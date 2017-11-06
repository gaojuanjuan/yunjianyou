package com.yunjy.jianyou.tools;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.blankj.utilcode.util.ScreenUtils;

import java.lang.reflect.Method;

public class ToastUtils {

    public static Toast mToast;

    /**
     * 建议使用AppLication的Context
     * @param mContext
     * @param msg
     */
    public static void showToast(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }



    public static Toast setToastAnimation(Toast mToast, int animID) {
        try {
            Method getWindowParams = mToast.getClass().getDeclaredMethod("getWindowParams");
            WindowManager.LayoutParams invoke = (WindowManager.LayoutParams) getWindowParams.invoke(mToast);
            if(animID >0){
                invoke.windowAnimations =animID;
            }else{
                invoke.windowAnimations = android.R.style.Animation_Toast;
            }
            invoke.width =  ScreenUtils.getScreenWidth();
            mToast.setGravity(Gravity.FILL_HORIZONTAL| Gravity.TOP,0,0);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e);
        } finally {
            return mToast;
        }
    }


}
