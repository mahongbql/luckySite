package com.luckysite.model;

/**
 * Created by mahongbin on 2018/11/24.
 */
public class PicParamModel {
    private Long userId;    //用户id
    private Long picId; //图片id

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }
}
