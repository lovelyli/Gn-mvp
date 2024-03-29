package com.project.gnet.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by GaoNan on 2016/6/7.
 */

public class PopoupUtils {

    public static PopupWindow popupWindow;
    public static PopupWindow initPop(Context context , int id, int animId, int color){
        View view = LayoutInflater.from(context).inflate(id,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        if (animId!=0){
            popupWindow.setAnimationStyle(animId);
        }
        //设置SelectPicPopupWindow弹出窗体的背景
        if (color!=0){
            popupWindow.setBackgroundDrawable(getColorDrawable(color));
        }else {
            popupWindow.setBackgroundDrawable(getColorDrawable(0xB2B2B2));
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        return popupWindow;
    }
    public static PopupWindow initPop(Context context , int id, int animId, int color, int height, int width){
        View view = LayoutInflater.from(context).inflate(id,null);
        popupWindow = new PopupWindow(view, width, height,true);
        if (animId!=0){
            popupWindow.setAnimationStyle(animId);
        }
        //设置SelectPicPopupWindow弹出窗体的背景
        if (color!=0){
            popupWindow.setBackgroundDrawable(getColorDrawable(color));
        }else {
            popupWindow.setBackgroundDrawable(getColorDrawable(0xB2B2B2));
        }

        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }
    public static ColorDrawable getColorDrawable(int color){

        return new ColorDrawable(color);
    }

    public void showPP(View container){
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }else {
            popupWindow.showAsDropDown(container,0, 0);
        }
    }
    public void dismissPP(){
        if (popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }

}
