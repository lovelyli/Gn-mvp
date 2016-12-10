package com.project.gnet.api;

/**
 * Created by GaoNan on 2016/12/9.
 */
public abstract class GSimpleCallback<T>  implements GCallBack<T>{
    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(GHttpException exception) {
    }

    @Override
    public void onNext(T t) {

    }
}
