package com.luckysite.service;

import com.luckysite.entity.Pic;

import java.util.List;

/**
 * Created by mahongbin on 2018/11/24.
 */
public interface GetImageService {

    /**
     * 获取所有图片信息
     * @return
     */
    List<Pic> getImage();
}
