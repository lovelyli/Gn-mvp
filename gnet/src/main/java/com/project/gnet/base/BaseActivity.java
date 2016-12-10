package com.project.gnet.base;//package com.project.ggnet.base;
//
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.project.ggnet.api.GHttpException;
//import com.project.ggnet.api.GSimpleCallback;
//import com.project.ggnet.api.Api;
//import com.project.ggnet.apiper;
//import com.project.ggnet.manager.ActivityPageManager;
//
//import java.io.IOException;
//
//import okhttp3.ResponseBody;
//import retrofit2.HttpException;
//import rx.Subscriber;
//import rx.subscriptions.CompositeSubscription;
//
///**
// * Created by GaoNan on 2016/12/9.
// */
//public class BaseActivity extends FragmentActivity {
//    /**
//     * 使用CompositeSubscription来持有所有的Subscriptions
//     */
//    protected CompositeSubscription mCompositeSubscription;
//    /**
//     * 页面布局的 根view
//     */
//    protected View mContentView;
//    /**
//     * Api类的包装 对象
//     */
//    protected ApiWrapper apiWrapper;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setResult(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
//        ActivityPageManager.getInstance().addActivity(this);
//    }
//
//    @Override
//    public void setContentView(int layoutResID) {
//        View view = LayoutInflater.from(this).inflate(layoutResID, null);
//        setContentView(view);
//    }
//
//    @Override
//    public void setContentView(View view) {
//        super.setContentView(view);
//        mContentView = view;
//        //初始化页面
//
//    }
//    /**
//     * 初始化 Api  更具需要初始化
//     */
//    public void initApi() {
//        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
//        mCompositeSubscription = new CompositeSubscription();
//        // 构建 ApiWrapper 对象
//        apiWrapper = new ApiWrapper();
//    }
//    public ApiWrapper getApiWrapper() {
//        if (apiWrapper == null) {
//            apiWrapper = new ApiWrapper();
//        }
//        return apiWrapper;
//    }
//    public CompositeSubscription getCompositeSubscription() {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        return mCompositeSubscription;
//    }
//    /**
//     * 创建观察者  这里对观察者进行过滤一次，过滤出我们想要的信息，错误的信息toast
//     */
//    protected <T> Subscriber newMySubscriber(final GSimpleCallback callback){
//        return  new Subscriber<T>(){
//            @Override
//            public void onCompleted() {
//                callback.onCompleted();
//            }
//            @Override
//            public void onError(Throwable e) {
//                if (e instanceof Api.APIException){
//                    Api.APIException exception = (Api.APIException) e;
//                    //TODO    提示信息
//                    Toast.makeText(BaseActivity.this,exception.message,Toast.LENGTH_SHORT).show();
//                }else if (e instanceof HttpException){
//                    ResponseBody body = ((HttpException)e).response().errorBody();
//                    try {
//                        String json = body.string();
//                        Gson gson = new Gson();
//                        GHttpException gHttpException = gson.fromJson(json,GHttpException.class);
//                        if (gHttpException != null && gHttpException.getMessage()!=null){
//                            //TODO 提示信息
//                            Toast.makeText(BaseActivity.this,gHttpException.getMessage(),Toast.LENGTH_SHORT).show();
//                            callback.onError(gHttpException);
//                        }
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//            @Override
//            public void onNext(T t) {
//                callback.onNext(t);
//            }
//        };
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //activity  释放view资源
//        ActivityPageManager.unbindReferences(mContentView);
//        ActivityPageManager.getInstance().removeActivity(this);
//        mContentView = null;
//        //一旦调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
//        // 如果还想使用CompositeSubscription，就必须在创建一个新的对象了。
//        if(mCompositeSubscription != null){
//            mCompositeSubscription.unsubscribe();
//        }
//    }
//}
