package com.luckysite.service;

import java.util.List;

public interface CacheService {
    /**
     * 获取指定类型key（文章或者图片）与指定id的浏览次数缓存数据
     * @param key
     * @param id
     * @return
     */
    Integer getViewNumber(String key, String id);

    /**
     * 获取指定类型key（文章或者图片）与指定id的收藏次数缓存数据
     * @param key
     * @param id
     * @return
     */
    Integer getCollectNumber(String key, String id);

    /**
     * 设置浏览次数
     * @param key
     * @param id
     */
    void setViewNumber(String key, String id);

    /**
     * 设置收藏次数
     * @param key
     * @param id
     * @param userId
     * @param status
     * @return
     */
    Boolean setCollectNumber(String key, String id, String userId, Boolean status);

    /**
     * 查看用户是否收藏了指定文章
     * @param key
     * @param id
     * @param userId
     * @return
     */
    Boolean checkIsCollect(String key, String id, String userId);
}
