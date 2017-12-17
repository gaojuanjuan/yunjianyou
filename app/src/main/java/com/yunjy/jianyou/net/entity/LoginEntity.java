package com.yunjy.jianyou.net.entity;

/**
 * Created by 高娟娟 on 2017/12/17.
 */

public class LoginEntity{

    /**
     * code : 200
     * message : 登录成功
     * User_ID : 17
     * IntervalTime : 3600
     * Token : mmzwncgh
     */

    private int code;
    private String message;
    private String User_ID;
    private String IntervalTime;
    private String Token;

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

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String User_ID) {
        this.User_ID = User_ID;
    }

    public String getIntervalTime() {
        return IntervalTime;
    }

    public void setIntervalTime(String IntervalTime) {
        this.IntervalTime = IntervalTime;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
}
