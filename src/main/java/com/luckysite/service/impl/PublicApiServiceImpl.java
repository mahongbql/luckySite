package com.luckysite.service.impl;

import com.luckysite.config.PublicApiConfig;
import com.luckysite.service.PublicApiService;
import com.luckysite.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PublicApiServiceImpl implements PublicApiService {

    private Logger log = LoggerFactory.getLogger(PublicApiServiceImpl.class);

    @Resource
    private PublicApiConfig publicApiConfig;

    @Override
    public String getDreamAnalytical(String q, int full) {
        publicApiConfig.getUrl(), publicApiConfig.getKey()
        String rtnMsg = HttpUtil.doGet();

        return null;
    }
}
