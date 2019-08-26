package com.luckysite.service;

/**
 * 公用接口
 */
public interface PublicApiService {

    /**
     * 周公解梦
     * @param q 梦境关键字，如：黄金 需要utf8 urlencode
     * @param full 是否显示详细信息，1:是 0:否，默认0
     * @return
     */
    String getDreamAnalytical(String q, int full);
}
