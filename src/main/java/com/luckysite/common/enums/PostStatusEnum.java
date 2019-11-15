package com.luckysite.common.enums;

import lombok.Getter;

@Getter
public enum PostStatusEnum {
    /**
     * 文章状态
     */

    PASS(1, "通过"),
    NOT_PASS(0, "不通过"),
    APPLICATION(2, "申请中");

    private int status;
    private String des;

    PostStatusEnum(int status, String des){
        this.des = des;
        this.status = status;
    }
}
