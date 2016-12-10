package com.project.testmvp.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.project.gnet.base.BasePreActivity;
import com.project.testmvp.R;
import com.project.testmvp.pre.Mypre;
import com.project.testmvp.vm.MyVm;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BasePreActivity<Mypre,MyVm> implements MyVm{
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.psw)
    EditText psw;
    @Bind(R.id.json_tv)
    TextView json_tv;
    @OnClick({R.id.next,R.id.send})
    public void send(View view){
        switch (view.getId()){
            case R.id.send:
                getPre().login(this);
                break;
            case R.id.next:
                gotoActivity(LogoutActivity.class);
                break;
        }
    }
    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initMvp(new Mypre(),this);
    }

    @Override
    public void initData() {

    }

    @Override
    public String getUsername() {
        return name.getText().toString();
    }

    @Override
    public String getUserpsw() {
        return psw.getText().toString();
    }

    @Override
    public void setJson(String json) {
        json_tv.setText(json);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPre().destroy();
    }
}
