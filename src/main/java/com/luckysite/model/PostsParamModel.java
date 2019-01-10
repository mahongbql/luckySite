package com.luckysite.model;

public class PostsParamModel {
    private Integer pageNum;    //分页起始
    private Integer pageSize;   //分页数量
    private Integer status;     //文章状态
    private Integer type;       //文章类型

    private String postId;
    private String userId;
    private Boolean collect;  //文章是否收藏

    public Boolean getCollect() {
        return this.collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return this.postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
