package com.luckysite.entity;

import java.util.Date;

public class UpLevel {
    private Long id;
    private Long userId;
    private Date sendTime;
    private Date confirmTime;
    private int status;
    private String type;

    public Long getId() {
        return this.id;
    }

    public int getStatus() {
        return this.status;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Date getConfirmTime() {
        return this.confirmTime;
    }

    public Date getSendTime() {
        return this.sendTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
