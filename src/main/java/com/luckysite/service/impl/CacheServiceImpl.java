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
        log.info("浏览次数：" + times + " 键值：" + key + id);

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
        log.info("浏览次数加一存入：" + viewNumber + " 键值：" + key + id);
    }

    @Override
    public Boolean setCollectNumber(String key, String id, String userId, Boolean status) {
        Object collectTimes = redisUtil.get(key + id);
        collectTimes = collectTimes == null ? "0" : collectTimes;
        Integer collectNum = Integer.parseInt(collectTimes.toString());

        if (status){
            collectNum += 1;
            redisUtil.set(key + id, collectNum);

            redisUtil.lSet(key+userId, id);
        }else {
            collectNum -= 1;
            redisUtil.set(key + id, collectNum);

            redisUtil.lRemove(key+userId, 1, id);
        }

        return status;
    }

    @Override
    public Boolean checkIsCollect(String key, String id, String userId) {
        boolean status = false;

        List<Object> list = redisUtil.lGet(key+userId, 0, -1);
        if(-1 != list.indexOf(id)){
            status = true;
        }

        return status;
    }
}
