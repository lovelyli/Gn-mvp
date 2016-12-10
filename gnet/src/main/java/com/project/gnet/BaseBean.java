package com.project.gnet;

import java.io.Serializable;

/**
 * Created by GaoNan on 2016/6/13.
 */

public class BaseBean implements Serializable {

    /**
     * msg : 请求成功
     * status : 200
     * userStatus : {"getPayCardFour":0,"getPayCardOne":0,"getPayCardReset":0,"infoSubFour":0,"infoSubOne":0,"infoSubReset":0,"subFourBadType":0,"subOneBadType":0,"subResetBadType":0,"userSubFour":0,"userSubOne":0,"userSubReset":0}
     */

    private String msg;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
