package com.luckysite.model;

/**
 * Created by mahongbin on 2018/11/24.
 */
public class PicParamModel {
    private int pageNum;    //分页起始
    private int pageSize;   //分页数量
    private Long uploadId; //批次
    private Integer type;   //图片类型

    public Integer getType() {
        type = type == null ? 0 : type;
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(Long uploadId) {
        this.uploadId = uploadId;
    }
}
