package com.luckysite.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PostPic {
    @Id
    private Long id;
    private String uploadName;
    private String url;
    private Long userId;
    private int status;
}
