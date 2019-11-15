package com.luckysite.common.enums;

import lombok.Getter;

@Getter
public enum PostPicStatusEnum {
    /**
     * 文章中图片状态
     */

    DELETEED(0, "已经删除"),
    USING(1, "正在使用");

    private int status;
    private String des;

    PostPicStatusEnum(int status, String des){
        this.des = des;
        this.status = status;
    }
}
