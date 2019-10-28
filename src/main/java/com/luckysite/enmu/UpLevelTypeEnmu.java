package com.luckysite.enmu;

import lombok.Getter;

@Getter
public enum  UpLevelTypeEnmu {
    /**
     * vip等级
     */
    UP_LEVEL_TO_VIP("vip"),

    /**
     * 作者等级
     */
    UP_LEVEL_TO_AUTHOR("author");

    private String type;

    UpLevelTypeEnmu(String type){
        this.type = type;
    }
}
