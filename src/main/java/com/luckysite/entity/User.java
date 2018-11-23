package com.luckysite.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String userName;
    private int freez;
    private Date registerTime;
    private Date loginTime;
    private Date refreshTime;
    private String sex;
    private int role;

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

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getSex() {
        return this.sex;
    }

    public int getRole() {
        return this.role;
    }
}
