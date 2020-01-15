package com.luckysite.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class User implements Serializable {
    @Id
    private Long userId;
    private String userName;
    private int freez;
    private Date registerTime;
    private Date loginTime;
    private Date refreshTime;
    private int role;
    private String nickName;    //用户昵称
    private String avatarUrl;   //用户头像

    public String getNickName() {
        return this.nickName;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFreez(int freez) {
        this.freez = freez;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public Date getLoginTime() {
        return this.loginTime;
    }

    public Date getRegisterTime() {
        return this.registerTime;
    }

    public int getFreez() {
        return this.freez;
    }

    public Date getRefreshTime() {
        return this.refreshTime;
    }

    public int getRole() {
        return this.role;
    }
}
