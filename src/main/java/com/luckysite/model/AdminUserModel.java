package com.luckysite.model;

public class AdminUserModel {
    private Integer status; //状态
    private Integer pageNum;    //分页起始
    private Integer pageSize;   //分页数量
    private String userId; //用户id

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
