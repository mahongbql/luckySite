package com.luckysite.entity;

import java.util.Date;

public class Pic {
    private Long id;
    private Long userId;
    private Date createTime;
    private String path;
    private String des;
    private int status;
    private Long uploadId;

    public void setUploadId(Long uploadId) {
        this.uploadId = uploadId;
    }

    public Long getUploadId() {
        return this.uploadId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return this.des;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getPath() {
        return this.path;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
