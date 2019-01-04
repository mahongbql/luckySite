package com.luckysite.entity;

import com.luckysite.util.TimeUtil;

import java.util.Date;

public class Post {
    private Long id;
    private String post_name;
    private String content;
    private int status;
    private Long userId;
    private Date send_time;
    private Date confirm_time;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConfirm_time() {
        return TimeUtil.format.format(this.confirm_time);
    }

    public String getSend_time() {

        return TimeUtil.format.format(this.send_time);
    }

    public void setConfirm_time(Date confirm_time) {
        this.confirm_time = confirm_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
