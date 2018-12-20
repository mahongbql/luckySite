package com.luckysite.enmu;

public enum  UpLevelEnmu {
    APPLICATION(0),
    HAS_ADOPTED(1);

    UpLevelEnmu(int status){
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return this.status;
    }
}
