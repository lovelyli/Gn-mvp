package com.project.gnet.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.project.gnet.R;

/**
 * Created by GaoNan on 2016/6/4.
 */

public class LoadingDialog extends Dialog implements DialogInterface.OnDismissListener {
    private LayoutInflater inflater;
    private OnDismissListener listener;
    private Context context;

    public LoadingDialog(Context context) {
        super(context);
        init(context);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }
    public void init(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void showDialog(OnDismissListener listener){
        try {
            if (this!=null&&this.isShowing()){
                return;
            }
            this.listener = listener;
            View contentview = inflater.inflate(R.layout.item_progress,null);
            setContentView(contentview);
            getWindow().setLayout(ScreenUtils.getScreenWidth(context),ScreenUtils.getScreenHeight(context));
            setCancelable(true);
            setOnDismissListener(this);
            show();
        }catch (Exception e){

        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (listener!=null){
            listener.onDismiss(dialog);
        }
    }
}
