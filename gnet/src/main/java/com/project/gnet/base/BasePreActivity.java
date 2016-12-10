package com.project.gnet.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.project.gnet.R;
import com.project.gnet.manager.ActivityPageManager;
import com.project.gnet.utils.LoadingDialog;

import java.util.List;
import butterknife.ButterKnife;

/**
 * Created by GaoNan on 2016/12/10.
 */
public abstract class  BasePreActivity<T extends BasePre,VM extends BaseVm> extends FragmentActivity {
    T t;
    public void initMvp(T t,VM vm){
        this.t = t;
        t.initApi();
        t.setVm(vm);
    }

    public T getPre() {
        return t;
    }

    /**
     * 页面布局的 根view
     */
    protected View mContentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ScreenUtils.setTranslucent(this);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setResult(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        View view = LayoutInflater.from(this).inflate(setLayoutId(), null);
        setContentView(view);
        mContentView = view;
        //初始化页面
        ActivityPageManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        initView();
        addListener();
        initData();
    }
    /**
     * 设置布局id
     * @return
     */
    public abstract int setLayoutId();

    /**
     * 初始化视图
     * @return
     */
    public abstract void initView();
    /**
     * 添加监听器
     */
    protected void addListener(){}

    /**
     * 加载数据
     * @return
     */
    public abstract void initData();

    /**
     * 跳转页面
     */
    public <T extends Activity> void gotoActivityT(Class<T> clz){
        Intent intent = new Intent(this,clz);
        startActivity(intent);
    }
    public <T extends FragmentActivity> void gotoActivity(Class<T> clz){
        Intent intent = new Intent(this,clz);
        startActivity(intent);
    }
    public <T extends FragmentActivity> void gotoActivity(Class<T> clz, Bundle data){
        Intent intent = new Intent(this,clz);
        intent.putExtra("data",data);
        startActivity(intent);
    }
    public <T extends FragmentActivity> void gotoActivityForResult(Class<T> clz, Bundle data, int requestcode){
        Intent intent = new Intent(this,clz);
        intent.putExtra("data",data);
        startActivityForResult(intent,requestcode);
    }
    /**
     * 跳转数据获取
     */
    public Bundle getActivityData(){
        Intent intent = getIntent();
        if (intent==null){
            return null;
        }
        return intent.getBundleExtra("data");
    }
    /**
     * 加载dialog
     */
    LoadingDialog dialog;
    public void showDialog(DialogInterface.OnDismissListener listener){
        if (dialog==null){
            dialog = new LoadingDialog(this, R.style.selectorDialog);
        }
        dialog.showDialog(listener);
    }
    public void showDialog(){
        if (dialog==null){
            dialog = new LoadingDialog(this, R.style.selectorDialog);
        }
        dialog.showDialog(null);
    }
    public void dismissDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
        ButterKnife.unbind(this);
    }
    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
