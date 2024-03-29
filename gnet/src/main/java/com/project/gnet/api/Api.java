package com.project.gnet.api;

import android.content.Context;

import com.project.gnet.impl.ApiService;
import com.project.gnet.utils.SSLContextUtil;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by GaoNan on 2016/12/9.
 */
public class Api{
    /**
     * 服务器地址
     */
    private static final String BASE_URL = "http://app.jiakaojingling.com/jkjl/api/";

    /**
     * 网络请求的请求头
     */
    private static final String HTTP_HEAD = "X-HB-Client-Type";
    private static final String FROM_ANDROID = "ayb-android";

    private ApiService service;
    private static Retrofit retrofit;
    private static boolean isSsl = false;

    /**
     * 获取接口对象
     * @return
     */
    public ApiService getService(Context context, boolean flag){
        isSsl = flag;
        if (service==null){
            service = getRetrofit(context).create(ApiService.class);
        }
        return service;
    }

    /**
     * 拦截器  给所有的请求添加消息头
     */
    private Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader(HTTP_HEAD,FROM_ANDROID)
                    .build();
            return chain.proceed(request);
        }
    };

    public Retrofit getRetrofit(Context context){
        if (retrofit==null){
            //log拦截器  打印所有的log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置  请求的缓存
            File cachFile = new File(context.getCacheDir(),"cache");
            Cache cache = new Cache(cachFile,1024*1024*50);
            OkHttpClient client;
            if (isSsl){
                client = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .addInterceptor(mInterceptor)
                        .sslSocketFactory(SSLContextUtil.getSSLContext(context,"gn").getSocketFactory())
                        .cache(cache)
                        .build();
            }else {
                client = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .addInterceptor(mInterceptor)
                        .cache(cache)
                        .build();
            }

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * 对Observable做了统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     */
    protected <T> Observable<T> applySchedulers(Observable<T> responseObservable){
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T t) {
                        return flatResponse(t);
                    }
                });
    }

    /**
     * 对网路接口返回的数据response进行分割操作
     * 对于json解析错误以及返回的响应实体为空的情况
     */
    public <T> Observable<T> flatResponse(final T response){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response!=null){
                    if (!subscriber.isUnsubscribed()){
                        subscriber.onNext(response);
                    }
                }else {
                    if (!subscriber.isUnsubscribed()){
                        subscriber.onError(new APIException("自定义异常类型","解析json错误或者服务器返回空的json"));
                    }
                    return;
                }
                if (!subscriber.isUnsubscribed()){
                    subscriber.onCompleted();
                }

            }
        });
    }

    /**
     *
     */
    public static class APIException extends Exception {
        public String code;
        public String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

    }


}
