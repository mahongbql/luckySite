package com.luckysite.enmu;

public enum  ResultCode {
    SUCCESS("成功", 1000),
    ERROR("失败", 1001);

    private String msg;
    private int code;

    ResultCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}
