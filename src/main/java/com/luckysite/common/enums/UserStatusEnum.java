package com.luckysite.common.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    /**
     * 冻结状态
     */
    FREEZ(1),

    /**
     * 非冻结状态
     */
    NOT_FREEZ(0);

    UserStatusEnum(int status){
        this.status = status;
    }

    private int status;
}
