package com.project.gnet.moudle;

import java.io.Serializable;

/**
 * Created by GaoNan on 2016/8/30.
 */
public class LoginBean implements Serializable {

    private String msg;
    private int status;
    private DataBean data;
    public DataBean getData() {
        return data;
    }
    public void setData(DataBean data) {
        this.data = data;
    }
    public static class DataBean {
        private String headImage;
        private String equitmentIdentity;
        private String userName;
        private int uid;
        private String phone;
        private int countyId;
        private int schoolId;
        private String schoolName;
        private String countyName;

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getEquitmentIdentity() {
            return equitmentIdentity;
        }

        public void setEquitmentIdentity(String equitmentIdentity) {
            this.equitmentIdentity = equitmentIdentity;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getCountyId() {
            return countyId;
        }

        public void setCountyId(int countyId) {
            this.countyId = countyId;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }



        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "LoginBean{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
