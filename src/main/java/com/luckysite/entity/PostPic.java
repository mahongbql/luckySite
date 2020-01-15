package com.luckysite.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class PostPic {
    private Long id;
    private String uploadName;
    private String url;
    private Long userId;
    private int status;
}
