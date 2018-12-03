package com.luckysite.model;

/**
 * Created by mahongbin on 2018/11/24.
 */
public class PicParamModel {
    private Integer pageNum;    //分页起始
    private Integer pageSize;   //分页数量
    private String picId;        //图片id
    private Integer type;   //图片类型
    private String userId;  //用户id

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public Integer getType() {
        type = type == null ? 0 : type;
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPicId() {
        return this.picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }
}
