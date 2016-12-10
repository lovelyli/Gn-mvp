package com.project.gnet;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;

import com.project.gnet.utils.ImagLoaderUtils;
import com.project.gnet.utils.LogUtils;
import com.project.gnet.utils.SharePreUtils;


/**
 * Created by gaonan on 15/9/22.
 */
public class BaseApp extends Application {
    private static BaseApp baseApp;
    private static PackageInfo packInfo;
    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    protected static Context mInstance;
    /**
     * 主线程ID
     */
    protected static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    protected static Thread mMainThread;
    /**
     * 主线程Handler
     */
    protected static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    protected static Looper mMainLooper;

    public static BaseApp getInstance() {
        return (BaseApp) mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;

        BaseGlobalParams.applicationContext = this;
        //TODO Log输出级别 上线需要修改
        BaseGlobalParams.Debuggable = LogUtils.LEVEL_ERROR;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
//        ImageLoader初始化
        ImagLoaderUtils.initImageLoader(this);

        SharePreUtils.getUtils().initUtils(this);
    }
    public static BaseApp getIns() {
        return baseApp;
    }
    /**
     * 获取应用的上下文
     *
     * @return 上下文
     */
    public static Context getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looperf
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
    /**
     * 获取apk包名路径
     */
    @SuppressLint("Override")
    public String getDataDir() {
        if (packInfo == null)
            getAppInfo();
        return packInfo != null && packInfo.applicationInfo != null ? packInfo.applicationInfo.dataDir : "";
    }

    private void getAppInfo() {
        // 获取packageManager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}

