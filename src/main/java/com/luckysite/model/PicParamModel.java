package com.luckysite.model;

/**
 * Created by mahongbin on 2018/11/24.
 */
public class PicParamModel {
    private int pageNum;    //分页起始
    private int pageSize;   //分页数量
    private Long userId;    //用户id
    private Long picId; //图片id

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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
