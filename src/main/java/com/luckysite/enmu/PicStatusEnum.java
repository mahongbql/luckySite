package com.luckysite.enmu;

public enum  PicStatusEnum {
    PASS(1, "通过"),
    NOT_PASS(0, "不通过");

    private int status;
    private String des;

    private PicStatusEnum(int status, String des){
        this.des = des;
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDes() {
        return this.des;
    }
}
