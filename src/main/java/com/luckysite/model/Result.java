package com.luckysite.model;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {
    private int code;
    private String msg;
    private Object object;

    public Result(){}

    public Result(int code, String msg, Object object){
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
