package com.luckysite.entity;

import lombok.Data;

@Data
public class PostPic {
    private Long id;
    private String uploadName;
    private String url;
    private Long userId;
    private int status;
}
