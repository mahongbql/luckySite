package com.luckysite.enmu;

public enum  UpLevelTypeEnmu {
    UP_LEVEL_TO_VIP("vip"),
    UP_LEVEL_TO_AUTHOR("author");

    private String type;

    private UpLevelTypeEnmu(String type){
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
