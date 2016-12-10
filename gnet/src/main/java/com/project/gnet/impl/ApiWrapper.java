package com.project.gnet.impl;


import android.content.Context;

import com.project.gnet.api.Api;
import com.project.gnet.moudle.LoginBean;

import rx.Observable;

/**
 * Created by GaoNan on 2016/12/9.
 */
public class ApiWrapper extends Api {
//    public Observable<LoginBean> getLoginInfo(Context context , LoginParams params){
//        return applySchedulers(getService(context).getPersonalInfo(params));
//    }
    public Observable<LoginBean> getLoginInfo(Context context , String phone , String psw, String equi){
        return applySchedulers(getService(context,false).getPersonalInfo(psw,phone,equi));
    }
}
