package com.luckysite.entity;

import com.luckysite.util.TimeUtil;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class UpLevel {
    @Id
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

    public String getConfirmTime() {
        return confirmTime == null ? "" : TimeUtil.format.format(confirmTime);
    }

    public String getSendTime() {
        return TimeUtil.format.format(sendTime);
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
