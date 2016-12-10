package com.project.gnet.base;//package com.project.ggnet.base;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.project.ggnet.api.GSimpleCallback;
//import com.project.ggnet.api.ApiWrapper
//import rx.Subscriber;
//import rx.subscriptions.CompositeSubscription;
//
///**
// * Created by GaoNan on 2016/12/9.
// */
//public abstract class BaseFragment extends Fragment {
//    public BaseActivity mContext;
//    public View mContentView = null;
//    /**
//     * 使用CompositionSubscription 来持有所有的Subscriptions
//     */
//    public CompositeSubscription mCompositeSubscription;
//    public ApiWrapper mApiWrapper;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = getBaseActivity();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (mContentView==null){
//            mContentView = inflater.inflate(onSetLayoutId(),container,false);
//            //TODO 初始化
//        }
//        return mContentView;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//    }
//
//    /**
//     * 初始化 Api  更具需要初始化
//     */
//    public void initApi() {
//        mCompositeSubscription  = mContext.getCompositeSubscription();
//        mApiWrapper = mContext.getApiWrapper();
//    }
//
//    public abstract int onSetLayoutId();
//
//    public <T> Subscriber newMySubscriber(final GSimpleCallback callback) {
//        return mContext.newMySubscriber(callback);
//    }
//
//    public BaseActivity getBaseActivity(){
//        return (BaseActivity) this.getActivity();
//    }
//}
