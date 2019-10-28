package com.luckysite.enmu;

import lombok.Getter;

@Getter
public enum UserStatusEnmu {
    /**
     * 冻结状态
     */
    FREEZ(1),

    /**
     * 非冻结状态
     */
    NOT_FREEZ(0);

    UserStatusEnmu(int status){
        this.status = status;
    }

    private int status;
}
