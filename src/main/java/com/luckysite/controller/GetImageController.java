package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.entity.Pic;
import com.luckysite.model.PicParamModel;
import com.luckysite.model.Result;
import com.luckysite.service.CacheService;
import com.luckysite.service.GetImageService;
import com.luckysite.util.CacheKeyUtil;
import com.luckysite.util.RedisUtil;
import com.luckysite.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mahongbin on 2018/11/24.
 */
@RestController
@RequestMapping("/getImage")
public class GetImageController {

    private Logger log = LoggerFactory.getLogger(GetImageController.class);

    @Autowired
    private GetImageService getImageService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisUtil redisUtil;

    //图片浏览次数
    private static final String VIEW_NUMBER = "view_number_";

    //图片收藏次数
    private static final String COLLECT_NUMBER = "collect_number_";

    //用户收藏记录
    private static final String PIC_COLLECT = "pic_";

    /**
     * 获取图片信息列表
     * @param picParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/getImageList")
    @ResponseBody
    public Result getImageList(PicParamModel picParamModel){
        List<Pic> picList = getImageService.getImage(picParamModel);

        for(Pic pic : picList){
            pic.setCollectNum(cacheService.getCollectNumber(CacheKeyUtil.PIC_COLLECT_NUMBER, pic.getUploadId().toString()));
            pic.setViewNumber(cacheService.getViewNumber(CacheKeyUtil.PIC_VIEW_NUMBER, pic.getId().toString()));
        }

        log.info("GetImageController-getImageList-参数：type = " + picParamModel.getType() + " pageSize = " + picParamModel.getPageSize() + " pageNumber = " + picParamModel.getPageNum() );

        log.info("GetImageController-getImageList-获取到的图片数量为：" + picList.size());

        Map<String, Object> result = new HashMap<>();
        result.put("data", picList);

        return ResultUtil.success(result);
    }

    /**
     * 获取指定批次的图片
     * @param picParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/getImageById")
    @ResponseBody
    public Result getImageById(PicParamModel picParamModel){
        List<Pic> picList = getImageService.getImageById(picParamModel);
        Long uploadId = picList.get(0).getUploadId();

        cacheService.setViewNumber(CacheKeyUtil.PIC_VIEW_NUMBER, uploadId.toString());

        for(Pic pic : picList){
            pic.setCollectNum(cacheService.getCollectNumber(CacheKeyUtil.PIC_COLLECT_NUMBER, pic.getUploadId().toString()));
            pic.setViewNumber(cacheService.getViewNumber(CacheKeyUtil.PIC_VIEW_NUMBER, pic.getId().toString()));
        }

        log.info("GetImageController-getImageList-获取到指定批次【" + picList.get(0).getUploadId() + "】的图片数量为：" + picList.size());

        Map<String, Object> result = new HashMap<>();
        result.put("data", picList);

        return ResultUtil.success(result);
    }

    /**
     * 用户添加收藏
     * @param picParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/collect")
    @ResponseBody
    public Result collect(PicParamModel picParamModel){
        setCollectNumber(picParamModel.getPicId(), picParamModel.getUserId());
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);
        return ResultUtil.success(result);
    }

    /**
     * 获取用户是否收藏
     * @param picParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/isCollect")
    @ResponseBody
    public Result isCollect(PicParamModel picParamModel){
        Map<String, Object> result = new HashMap<>();
        result.put("status", getCollect(picParamModel.getPicId(), picParamModel.getUserId()));
        return ResultUtil.success(result);
    }

    /**
     * 用户取消收藏
     * @param picParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/cancel")
    @ResponseBody
    public Result cancel(PicParamModel picParamModel){
        cancelCollect(picParamModel.getPicId(), picParamModel.getUserId());
        Map<String, Object> result = new HashMap<>();
        result.put("status", false);
        return ResultUtil.success(result);
    }

    /**
     * 用户取消收藏
     * @param id
     * @param userId
     */
    private void cancelCollect(String id, String userId){
        redisUtil.lRemove(PIC_COLLECT+userId, 1, id);

        Object collectTimes = redisUtil.get(COLLECT_NUMBER + id);

        if(null == collectTimes){
            redisUtil.set(COLLECT_NUMBER + id, 0);
        }else{
            Integer collectNum = Integer.parseInt(collectTimes.toString());
            collectNum -= 1;
            redisUtil.set(COLLECT_NUMBER + id, collectNum);
        }
    }

    /**
     * 设置收藏次数
     * @param id
     */
    private void setCollectNumber(String id, String userId) {
        Object collectTimes = redisUtil.get(COLLECT_NUMBER + id);

        collectTimes = collectTimes == null ? "0" : collectTimes;
        Integer collectNum = Integer.parseInt(collectTimes.toString());
        collectNum += 1;
        redisUtil.set(COLLECT_NUMBER + id, collectNum);

        redisUtil.lSet(PIC_COLLECT+userId, id);
    }

    /**
     * 用户是否收藏了该图片
     * @return
     */
    private boolean getCollect(String id, String userId){
        boolean status = false;
        List<Object> list = redisUtil.lGet(PIC_COLLECT+userId, 0, -1);

        if(-1 != list.indexOf(id)){
            status = true;
        }

        return status;
    }

    @RequestMapping("/getCollectNumber")
    public @ResponseBody
    Result getCollectNumber(@RequestParam("userId") String userId){
        Map<String, Object> result = new HashMap<>();

        Long number = redisUtil.lGetListSize(PIC_COLLECT+userId);
        result.put("number", number);
        return ResultUtil.success(result);
    }
}
