package com.project.gnet.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.project.gnet.base.BasePreActivity;

/**
 * Created by GaoNan on 2016/6/6.
 */

public class ScreenUtils {
    static int screenWidth = 0;
    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
    public static int getScreenWidth(Context context){
        if (screenWidth!=0){
            return screenWidth;
        }
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    public static void setTranslucent(BasePreActivity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    /**
     * 获取手机分辨率
     */
    public static String getDisplayMetrix(Context context) {
        int width = 0;
        int height = 0;
        if (context != null) {
            SharedPreferences DiaplayMetrixInfo = context.getSharedPreferences("display_metrix_info", 0);
            if (context instanceof Activity) {
                WindowManager windowManager = ((Activity) context).getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                DisplayMetrics dm = new DisplayMetrics();
                display.getMetrics(dm);
                width = dm.widthPixels;
                height = dm.heightPixels;

                SharedPreferences.Editor editor = DiaplayMetrixInfo.edit();
                editor.putInt("width", width);
                editor.putInt("height", height);
                editor.commit();
            } else {
                width = DiaplayMetrixInfo.getInt("width", 0);
                height = DiaplayMetrixInfo.getInt("height", 0);
            }
        }
        return width + "×" + height;
    }
    /**
     * 关闭系统的软键盘
     * @param activity
     */
    public static void dismissSoftKeyboard(Activity activity)
    {
        View view = activity.getWindow().peekDecorView();
        if (view != null)
        {
            InputMethodManager inputmanger = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @return 返回像素值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @return 返回dp值
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
