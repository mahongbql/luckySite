package com.luckysite.enmu;

import lombok.Getter;

@Getter
public enum  PicStatusEnum {
    /**
     * 图片状态
     */

    PASS(1, "通过"),
    NOT_PASS(0, "不通过"),
    APPLICATION(2, "申请中"),

    ;

    private Integer status;
    private String des;

    PicStatusEnum(Integer status, String des){
        this.des = des;
        this.status = status;
    }
}
