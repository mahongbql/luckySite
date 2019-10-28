package com.luckysite.enmu;

import lombok.Getter;

@Getter
public enum  UpLevelEnmu {
    /**
     * 申请中
     */
    APPLICATION(0),

    /**
     * 已经采用
     */
    HAS_ADOPTED(1);

    UpLevelEnmu(int status){
        this.status = status;
    }

    private int status;
}
