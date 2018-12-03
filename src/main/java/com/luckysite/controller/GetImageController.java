package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.entity.Pic;
import com.luckysite.model.PicParamModel;
import com.luckysite.model.Result;
import com.luckysite.service.GetImageService;
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
        List<Pic> picList = getViewNumber(getImageService.getImage(picParamModel));
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
        setViewNumber(uploadId);
        picList = getViewNumber(picList);
        log.info("GetImageController-getImageList-获取到指定批次【" + picList.get(0).getUploadId() + "】的图片数量为：" + picList.size());

        Map<String, Object> result = new HashMap<>();
        result.put("data", picList);

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
        return ResultUtil.success();
    }

    /**
     * 用户取消收藏
     * @param id
     * @param userId
     */
    private void cancelCollect(String id, String userId){
        redisUtil.hdel(userId, COLLECT_NUMBER + id);
    }

    /**
     * 获得图片浏览次数
     * @return
     */
    private List<Pic> getViewNumber(List<Pic> picList){
        for(Pic pic : picList){
            Object times = redisUtil.get(VIEW_NUMBER + pic.getUploadId());
            Object collectTimes = redisUtil.get(COLLECT_NUMBER + pic.getId());

            times = times == null ? "0" : times;
            collectTimes = collectTimes == null ? "0" : collectTimes;

            Integer viewNumber = Integer.parseInt(times.toString());
            Integer collectNum = Integer.parseInt(collectTimes.toString());

            pic.setViewNumber(viewNumber);
            pic.setCollectNum(collectNum);
            log.info("GetImageController-getViewNumber-批次【" + pic.getUploadId() + "】浏览次数为：" + viewNumber);
        }

        return picList;
    }

    /**
     * 设置浏览次数
     * @param uploadId
     */
    private void setViewNumber(Long uploadId){
        Object times = redisUtil.get(VIEW_NUMBER + uploadId);
        times = times == null ? "0" : times;
        Integer viewNumber = Integer.parseInt(times.toString());
        viewNumber += 1;
        redisUtil.set(VIEW_NUMBER + uploadId, viewNumber);
        log.info("GetImageController-setViewNumber-批次【" + uploadId + "】浏览次数 + 1，变为：" + viewNumber);
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

        redisUtil.hset(userId, PIC_COLLECT + id, true);
    }

    /**
     * 用户是否收藏了该图片
     * @return
     */
    private boolean getCollect(String id, String userId){
        boolean status = false;
        Object obj = redisUtil.hget(userId, PIC_COLLECT + id);

        if(null != obj){
            status = true;
        }

        return status;
    }
}
