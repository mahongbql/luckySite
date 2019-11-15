package com.luckysite.common.enums;

import lombok.Getter;

@Getter
public enum UpLevelTypeEnum {
    /**
     * vip等级
     */
    UP_LEVEL_TO_VIP("vip"),

    /**
     * 作者等级
     */
    UP_LEVEL_TO_AUTHOR("author");

    private String type;

    UpLevelTypeEnum(String type){
        this.type = type;
    }
}
