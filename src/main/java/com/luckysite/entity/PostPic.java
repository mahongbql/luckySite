package com.luckysite.entity;

public class PostPic {
    private Long id;
    private String uploadName;
    private String url;
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUploadName() {
        return this.uploadName;
    }
}
