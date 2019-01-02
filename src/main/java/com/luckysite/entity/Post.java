package com.luckysite.entity;

import java.util.Date;

public class Post {
    private Long id;
    private String post_name;
    private String post_position;
    private int status;
    private Long userId;
    private Date sendTime;
    private Date confirmTime;

    public Date getSendTime() {
        return this.sendTime;
    }

    public Date getConfirmTime() {
        return this.confirmTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public int getStatus() {
        return this.status;
    }

    public String getPost_name() {
        return this.post_name;
    }

    public String getPost_position() {
        return this.post_position;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public void setPost_position(String post_position) {
        this.post_position = post_position;
    }
}
