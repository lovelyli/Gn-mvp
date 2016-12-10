package com.project.gnet.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.project.gnet.R;
import com.project.gnet.utils.LoadingDialog;

/**
 * Created by GaoNan on 2016/12/10.
 */
public abstract class BasePreFragment<T extends BasePre,VM extends BaseVm> extends Fragment {
    T t;
    public void initMvp(T t, VM vm){
        this.t = t;
        t.initApi();
        t.setVm(vm);
    }

    public T getPre() {
        return t;
    }

    public View view = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view!=null){
            ViewGroup parent = null;
            ViewParent v =  view.getParent();
            if(v instanceof ViewGroup){
                parent = (ViewGroup) v;
            }
            if (null != parent) {
                parent.removeView(view);
            }
        }else {
            view = inflater.inflate(setLayoutId(),null);
            initView(view);
            addListener();
            initData(view);
        }
        return view;

    }
    /**
     * 设置布局id
     * @return
     */
    public abstract int setLayoutId();
    /**
     * 初始化师徒
     * @return
     */
    public abstract void initView(View view);
    /**
     * 添加监听器
     * @return
     */
    public void addListener(){}
    /**
     * 加载数据
     * @return
     */
    public abstract void initData(View view);

    /**
     * view初始化
     */
    public View findView(int id){
        return view.findViewById(id);
    }

    /**
     * 跳转页面
     */
    /**
     * 跳转页面
     */
    public <T extends Activity> void gotoActivityT(Class<T> clz){
        Intent intent = new Intent(getActivity(),clz);
        startActivity(intent);
    }
    public <T extends Activity> void gotoActivityT(Class<T> clz, Bundle data){
        Intent intent = new Intent(getActivity(),clz);
        intent.putExtra("data",data);
        startActivity(intent);
    }
    public <T extends FragmentActivity> void gotoActivity(Class<T> clz){
        Intent intent = new Intent(getActivity(),clz);
        getActivity().startActivity(intent);
    }
    public <T extends FragmentActivity> void gotoActivity(Class<T> clz, Bundle data){
        Intent intent = new Intent(getActivity(),clz);
        intent.putExtra("data",data);
        getActivity().startActivity(intent);
    }
    public <T extends FragmentActivity> void gotoActivityForResult(Class<T> clz, Bundle data, int requestcode){
        Intent intent = new Intent(getActivity(),clz);
        intent.putExtra("data",data);
        getActivity().startActivityForResult(intent,requestcode);
    }
    /**
     * 加载dialog
     */
    LoadingDialog dialog;
    public void showDialog(DialogInterface.OnDismissListener listener){
        if (dialog==null){
            dialog = new LoadingDialog(getActivity(), R.style.selectorDialog);
        }
        dialog.showDialog(listener);
    }
    public void showDialog(){
        showDialog(null);
    }
    public void dismissDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dismissDialog();
    }

}
