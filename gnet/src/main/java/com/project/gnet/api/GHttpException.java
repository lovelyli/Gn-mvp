package com.project.gnet.api;

/**
 * Created by GaoNan on 2016/12/9.
 */
public class GHttpException {

    private String code;
    private String message;
    private String body;

    public GHttpException(String code, String message, String body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
