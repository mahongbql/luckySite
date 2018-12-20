package com.luckysite.model;

public class AdminPicModel {
    private Integer status; //状态
    private Integer pageNum;    //分页起始
    private Integer pageSize;   //分页数量
    private String picId;   //图片id

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getPicId() {
        return this.picId;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
