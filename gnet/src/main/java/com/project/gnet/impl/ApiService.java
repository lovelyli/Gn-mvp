package com.project.gnet.impl;


import com.project.gnet.moudle.LoginBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by GaoNan on 2016/12/9.
 */
public interface ApiService {

    /**
     * 登录获取个人信息
     */
    @POST("member/userLogin")
    Observable<LoginBean> getPersonalInfo(@Query("password") String psw
            , @Query("phone") String phone
            , @Query("equitment") String equitment);

}
