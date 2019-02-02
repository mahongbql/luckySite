package com.luckysite.enmu;

public enum PostPicStatusEnmu {
    DELETEED(0, "已经删除"),
    USING(1, "正在使用");

    private int status;
    private String des;

    private PostPicStatusEnmu(int status, String des){
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
