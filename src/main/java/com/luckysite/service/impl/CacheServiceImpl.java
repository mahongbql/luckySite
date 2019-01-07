package com.luckysite.service.impl;

import com.luckysite.service.CacheService;
import com.luckysite.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {

    private Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Integer getViewNumber(String key, String id) {
        Object times = redisUtil.get(key + id);
        times = times == null ? "0" : times;

        return Integer.parseInt(times.toString());
    }

    @Override
    public Integer getCollectNumber(String key, String id) {
        Object times = redisUtil.get(key + id);
        times = times == null ? "0" : times;

        return Integer.parseInt(times.toString());
    }
}
