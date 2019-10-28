package com.luckysite.enmu;

import lombok.Getter;

@Getter
public enum PostPicStatusEnmu {
    /**
     * 文章中图片状态
     */

    DELETEED(0, "已经删除"),
    USING(1, "正在使用");

    private int status;
    private String des;

    PostPicStatusEnmu(int status, String des){
        this.des = des;
        this.status = status;
    }
}
