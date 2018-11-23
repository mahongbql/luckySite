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
}
