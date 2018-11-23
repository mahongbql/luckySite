package com.luckysite.util;

import com.luckysite.model.Result;

import java.util.Map;

public class ResultUtil{

    /**
     * 失败
     * @param code
     * @param msg
     * @return
     */
    public static Result error(String msg, int code) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 成功
     * @param map
     * @return
     */
    public static Result success(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    /**
     * 成功
     * @return
     */
    public static Result success() {
        return new Result();
    }
}
