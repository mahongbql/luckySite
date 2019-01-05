package com.luckysite.model;

public class UserDataModel {
    private Integer pageNum;    //分页起始
    private Integer pageSize;   //分页数量
    private String userId;  //用户id
    private Integer type;   //获取文章 0    获取图片 1
    private String nickName;    //用户昵称
    private String avatarUrl;   //用户头像

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
