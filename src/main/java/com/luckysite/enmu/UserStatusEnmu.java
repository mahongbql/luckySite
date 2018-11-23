package com.luckysite.enmu;

public enum UserStatusEnmu {
    FREEZ(1),
    NOT_FREEZ(0);

    private UserStatusEnmu(int status){
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
