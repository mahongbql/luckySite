package com.luckysite.model;

public class PicResultModel {
    private Long id;            //自增id
    private String path;    //图片路径
    private String des;     //图片描述
    private int status;     //图片状态
    private Long uploadId;  //上传编号
    private String userIcon;    //用户头像
    private String nickName; //用户昵称

    public void setId(Long id) {
        this.id = id;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUploadId(Long uploadId) {
        this.uploadId = uploadId;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Long getId() {
        return this.id;
    }

    public String getPath() {
        return this.path;
    }

    public int getStatus() {
        return this.status;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getDes() {
        return this.des;
    }

    public Long getUploadId() {
        return this.uploadId;
    }

    public String getUserIcon() {
        return this.userIcon;
    }
}
