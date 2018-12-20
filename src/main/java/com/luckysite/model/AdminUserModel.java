package com.luckysite.model;

public class AdminUserModel {
    private Integer status; //状态
    private Integer pageNum;    //分页起始
    private Integer pageSize;   //分页数量
    private String userId; //用户id
    private String type;    //升级类型

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

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
