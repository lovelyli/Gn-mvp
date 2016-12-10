package com.project.gnet.base;

import com.project.gnet.impl.ApiWrapper;
import com.project.gnet.api.GHttpException;
import com.project.gnet.api.GSimpleCallback;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by GaoNan on 2016/12/10.
 */
public abstract class BasePre <VM extends BaseVm>{
    VM vm;
    public void setVm(VM vm){
        this.vm = vm;
    }

    public VM getVm() {
        return vm;
    }

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;
    /**
     * Api类的包装 对象
     */
    protected ApiWrapper apiWrapper;
    /**
     * 初始化 Api  更具需要初始化
     */
    public void initApi() {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeSubscription();
        // 构建 ApiWrapper 对象
        apiWrapper = new ApiWrapper();
    }
    public ApiWrapper getApiWrapper() {
        if (apiWrapper == null) {
            apiWrapper = new ApiWrapper();
        }
        return apiWrapper;
    }
    public CompositeSubscription getCompositeSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }
    /**
     * 创建观察者  这里对观察者进行过滤一次，过滤出我们想要的信息，错误的信息toast
     */
    protected <T> Subscriber newMySubscriber(final GSimpleCallback<T> callback){
        return  new Subscriber<T>(){
            @Override
            public void onCompleted() {
                callback.onCompleted();
            }
            @Override
            public void onError(Throwable e) {
                callback.onError(new GHttpException("100",e.getMessage(),""));
            }
            @Override
            public void onNext(T t) {
                callback.onNext(t);
            }
        };
    }

    public void destroy(){
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }



}
