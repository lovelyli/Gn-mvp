package com.project.gnet.api;

/**
 * Created by GaoNan on 2016/12/9.
 */
public interface GCallBack<T> {
    void onCompleted();
    void onError(GHttpException ghttpexception);
    void onNext(T t);
}
