package com.luckysite.util;

import com.luckysite.enmu.ResultCode;
import com.luckysite.model.Result;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    /**
     * 失败
     * @param map
     * @return
     */
    public static Result error(Map<String, Object> map) {
        Result r = new Result();
        r.setCode(ResultCode.ERROR.getCode());
        r.setMsg(ResultCode.ERROR.getMsg());
        r.setData(map);
        return r;
    }

    /**
     * 失败
     * @param code
     * @param msg
     * @param map
     * @return
     */
    public static Result error(int code, String msg, Map<String, Object> map) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(map);
        return r;
    }

    /**
     * 成功
     * @param map
     * @return
     */
    public static Result success(Map<String, Object> map) {
        Result r = new Result();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMsg(ResultCode.SUCCESS.getMsg());
        r.setData(map);
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
