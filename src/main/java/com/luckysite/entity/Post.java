package com.luckysite.entity;

import com.luckysite.util.TimeUtil;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class Post {
    @Id
    private Long id;
    private String post_name;
    private String content;
    private int status;
    private Long userId;
    private Date send_time;
    private Date confirm_time;
    private String title;

    private Integer type;   //0:科技  1:生活

    public Integer viewNumber;     //浏览次数
    public Integer collectNumber;  //收藏数量

    private String nickName;
    private String avatarUrl;

    private String url;

    private Boolean isCollect;  //用户是否收藏该文章

    public Boolean getCollect() {
        return this.isCollect;
    }

    public void setCollect(Boolean collect) {
        this.isCollect = collect;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public Integer getCollectNumber() {
        return this.collectNumber;
    }

    public Integer getViewNumber() {
        return this.viewNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

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
