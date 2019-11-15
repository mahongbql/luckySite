package com.luckysite.common.enums;

import lombok.Getter;

@Getter
public enum UpLevelEnum {
    /**
     * 申请中
     */
    APPLICATION(0),

    /**
     * 已经采用
     */
    HAS_ADOPTED(1);

    UpLevelEnum(int status){
        this.status = status;
    }

    private int status;
}
