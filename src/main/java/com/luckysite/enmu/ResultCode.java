package com.luckysite.enmu;

import lombok.Getter;

@Getter
public enum  ResultCode {
    /**
     * 返回状态码
     */

    SUCCESS("成功", 1000),
    ERROR("失败", 1001);

    private String msg;
    private int code;

    ResultCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
