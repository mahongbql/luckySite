package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AuthConfig;
import com.luckysite.config.UrlListConfig;
import com.luckysite.model.Result;
import com.luckysite.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("more")
public class MoreController {

    @Autowired
    private UrlListConfig urlListConfig;

    /**
     * 获得视频列表
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/urlList")
    @ResponseBody
    public Result<List<String>> getUrlList(){
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(urlListConfig.getUrlList().split(",")));
        map.put("list", list);

        return ResultUtil.success(map);
    }
}
