package com.project.testmvp.pre;

import android.content.Context;

import com.project.gnet.api.GHttpException;
import com.project.gnet.api.GSimpleCallback;
import com.project.gnet.base.BasePre;
import com.project.gnet.moudle.LoginBean;
import com.project.gnet.utils.UIUtils;
import com.project.testmvp.vm.MyVm;

import rx.Subscription;

/**
 * Created by iningke on 2016/12/10.
 */
public class Mypre extends BasePre<MyVm> {
    public void login(Context context){
        Subscription subscription = getApiWrapper().getLoginInfo(context, getVm().getUsername(), getVm().getUserpsw(), "")
                .subscribe(newMySubscriber(new GSimpleCallback<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        UIUtils.showToastSafe("请求完成");
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        super.onNext(loginBean);
                        UIUtils.showToastSafe("登录相关:"+loginBean.getMsg());
                        getVm().setJson(loginBean.toString());
                    }

                    @Override
                    public void onError(GHttpException exception) {
                        super.onError(exception);
                        UIUtils.showToastSafe("请求失败");
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
