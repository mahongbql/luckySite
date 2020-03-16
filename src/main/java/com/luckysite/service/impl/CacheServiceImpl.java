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

    @Override
    public void setViewNumber(String key, String id) {
        Object times = redisUtil.get(key + id);
        times = times == null ? "0" : times;
        Integer viewNumber = Integer.parseInt(times.toString());
        viewNumber += 1;
        redisUtil.set(key + id, viewNumber);
    }

    @Override
    public Boolean setCollectNumber(String key1, String key2, String id, String userId, Boolean status) {
        Object collectTimes = redisUtil.get(key1 + id);
        collectTimes = collectTimes == null ? "0" : collectTimes;
        Integer collectNum = Integer.parseInt(collectTimes.toString());

        if (status){
            collectNum += 1;
            redisUtil.set(key1 + id, collectNum);

            redisUtil.lSet(key2 + userId, id);
        }else {
            collectNum -= 1;
            redisUtil.set(key1 + id, collectNum);

            redisUtil.lRemove(key2 + userId, 1, id);
        }

        return status;
    }

    @Override
    public Boolean checkIsCollect(String key, String id, String userId) {
        boolean status = false;

        List<Object> list = redisUtil.lGet(key+userId, 0, -1);
        if(list != null && -1 != list.indexOf(id)){
            status = true;
        }

        return status;
    }
}
