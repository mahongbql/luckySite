package com.luckysite.controller;

import com.luckysite.dto.DreamAnalyticalDTO;
import com.luckysite.service.PublicApiService;
import com.luckysite.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author mahongbin
 * @date 2019/8/27 14:16
 * @Description
 */
@Api(value = "聚合数据接口", tags = "聚合数据接口")
@RestController
@RequestMapping("/publicApi")
public class PublicApiController {

    @Resource
    private PublicApiService publicApiService;

    @ApiOperation(value = "周公解梦", notes = "周公解梦")
    @RequestMapping(value = "getDreamAnalytical", method = RequestMethod.GET)
    public ResponseResult<DreamAnalyticalDTO> getDreamAnalytical(@RequestParam("q") String q) {
        return publicApiService.getDreamAnalytical(q, 1);
    }
}
