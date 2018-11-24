package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.enmu.ResultCode;
import com.luckysite.entity.Pic;
import com.luckysite.model.PicParamModel;
import com.luckysite.model.Result;
import com.luckysite.service.GetImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mahongbin on 2018/11/24.
 */
@RestController
@RequestMapping("/getImage")
public class GetImageController {

    private Logger log = LoggerFactory.getLogger(GetImageController.class);

    @Autowired
    private GetImageService getImageService;

    /**
     * 获取图片信息列表
     * @param picParamModel
     * @return
     */
    @Auth(role = AuthConfig.VISTOR)
    @RequestMapping("/getImageList")
    public Result getImageList(@RequestParam PicParamModel picParamModel){
        List<Pic> picList = getImageService.getImage();
        log.info("GetImageController-getImageList-获取到的图片数量为：" + picList.size());

        Result result = new Result(ResultCode.SUCCESS.getCode(), "图片获取成功");
        result.put("data", picList);

        return result;
    }
}
