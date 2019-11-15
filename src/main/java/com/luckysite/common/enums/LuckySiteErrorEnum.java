package com.luckysite.common.enums;

/**
 * @author mahongbin
 * @date 2019/11/15 15:51
 * @Description
 */
public enum LuckySiteErrorEnum {

    /**
     * 请求成功
     */
    SUCCESS("0", "成功"),

    //                              系统错误
    //========================================================================//


    //                              业务错误
    //========================================================================//
    REGISTER_ERROR("10001", "用户注册异常"),

    ;

    /**
     * 操作代码
     */
    private final String code;

    /**
     * 外部描述
     */
    private final String msg;

    LuckySiteErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getResponseCode() {
        return this.code;
    }

    public String getResponseMessage() {
        return this.msg;
    }
}
