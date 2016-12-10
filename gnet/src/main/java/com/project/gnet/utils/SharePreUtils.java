package com.project.gnet.utils;

import android.content.SharedPreferences;

import com.project.gnet.BaseApp;

/**
 * Created by GaoNan on 2016/7/21.
 */
public class SharePreUtils {
    static SharePreUtils utils;
    private SharePreUtils() {
    }
    public static SharePreUtils getUtils(){
        if (utils==null){
            utils = new SharePreUtils();
        }
        return utils;
    }
    BaseApp app;
    public void initUtils(BaseApp app){
        this.app = app;
    }
    public int getIntCache(String key){
        SharedPreferences preferences = app.getSharedPreferences("data",app.MODE_PRIVATE);
        return preferences.getInt(key,0);
    }
    public void putIntCache(String key, int value) {
        SharedPreferences preferences = app.getSharedPreferences("data",app.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    public String getStringCache(String key){
        SharedPreferences preferences = app.getSharedPreferences("data",app.MODE_PRIVATE);
        return preferences.getString(key, "");
    }


    public void putStringCache(String key, String value) {
        SharedPreferences preferences = app.getSharedPreferences("data",app.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public void removeIntCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data",app.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }
    public void removeStringCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data",app.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }
}
