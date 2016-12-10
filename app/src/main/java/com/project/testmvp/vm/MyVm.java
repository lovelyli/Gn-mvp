package com.project.testmvp.vm;

import com.project.gnet.base.BaseVm;

/**
 * Created by iningke on 2016/12/10.
 */
public interface MyVm extends BaseVm {
    String getUsername();
    String getUserpsw();
    void setJson(String json);
}
