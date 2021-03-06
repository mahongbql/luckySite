package com.luckysite.service;

import com.luckysite.dto.publicApi.DreamAnalyticalDTO;
import com.luckysite.dto.publicApi.LaoHuangLiDTO;
import com.luckysite.util.ResponseResult;

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
    ResponseResult<DreamAnalyticalDTO> getDreamAnalytical(String q, int full);

    /**
     * 老黄历
     * @param date 日期，格式2014-09-09
     * @return
     */
    ResponseResult<LaoHuangLiDTO> getLaoHuangLi(String date);
}
