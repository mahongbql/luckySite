package com.luckysite.entity;

import java.util.Date;

public class Pic {
    private Long id;            //自增id
    private Long userId;        //上传用户id
    private Date createTime;    //创建时间
    private String path;    //图片路径
    private String des;     //图片描述
    private int status;     //图片状态
    private Long uploadId;  //上传编号

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
