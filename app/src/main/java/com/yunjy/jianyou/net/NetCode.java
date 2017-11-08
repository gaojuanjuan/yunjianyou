package com.yunjy.jianyou.net;

/**
 * Created by zt on 2017/11/8.
 */

public enum NetCode {

    Success(200,"请求成功");




    public int code;
    public String desc;

    NetCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
