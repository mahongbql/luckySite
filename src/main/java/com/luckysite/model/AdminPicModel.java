package com.luckysite.model;

import lombok.Data;

@Data
public class AdminPicModel {
    /**
     * 状态
     */
    private Integer status;

    /**
     * 分页起始
     */
    private Integer pageNum;

    /**
     * 分页数量
     */
    private Integer pageSize;

    /**
     * 图片id
     */
    private String picId;
}
