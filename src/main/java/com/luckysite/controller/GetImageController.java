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

    /**
     * 获取图片信息列表
     * @param picParamModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/getImageList")
    @ResponseBody
    public Result getImageList(PicParamModel picParamModel){
//        List<Pic> picList = getViewNumber(getImageService.getImage(picParamModel));
        log.info("GetImageController-getImageList-参数：type = " + picParamModel.getType() + " pageSize = " + picParamModel.getPageSize() + " pageNumber = " + picParamModel.getPageNum() );
        List<Pic> picList = getImageService.getImage(picParamModel);
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
//        Long uploadId = picList.get(0).getUploadId();
//        setViewNumber(uploadId);
//        picList = getViewNumber(picList);
        log.info("GetImageController-getImageList-获取到指定批次【" + picList.get(0).getUploadId() + "】的图片数量为：" + picList.size());

        Map<String, Object> result = new HashMap<>();
        result.put("data", picList);

        return ResultUtil.success(result);
    }

    /**
     * 获得图片浏览次数
     * @return
     */
    private List<Pic> getViewNumber(List<Pic> picList){
        for(Pic pic : picList){
            Integer viewNumber = Integer.parseInt(redisUtil.get(VIEW_NUMBER + pic.getUploadId()).toString());
            pic.setViewNumber(viewNumber);
            log.info("GetImageController-getViewNumber-批次【" + pic.getUploadId() + "】浏览次数为：" + viewNumber);
        }

        return picList;
    }

    /**
     * 设置picId
     * @param uploadId
     */
    private void setViewNumber(Long uploadId){
        Object times = redisUtil.get(VIEW_NUMBER + uploadId);
        times = times == null ? "0" : times;
        Integer viewNumber = Integer.parseInt(times.toString());
        viewNumber = viewNumber == null ? 1 : viewNumber+1;
        redisUtil.set(VIEW_NUMBER + uploadId, viewNumber);
        log.info("GetImageController-setViewNumber-批次【" + uploadId + "】浏览次数 + 1，变为：" + viewNumber);
    }
}
