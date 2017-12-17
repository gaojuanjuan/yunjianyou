package com.yunjy.jianyou.net.entity;

/**
 * Created by zt on 2017/11/8.
 */

public class BaseEntity<T>{

    /**
     * @see com.yunjy.jianyou.net.NetCode
     */
    int code;

    String message;

    T  data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
