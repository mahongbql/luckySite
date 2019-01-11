package com.luckysite.model;

public class AdminPostsModel {
    private Integer pageNum;    //分页起始
    private Integer pageSize;   //分页数量
    private Integer status;
    private String postName;

    public String getPostName() {
        return this.postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
