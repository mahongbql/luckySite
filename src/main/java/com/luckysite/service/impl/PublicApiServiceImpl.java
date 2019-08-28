package com.luckysite.service.impl;

import com.google.gson.Gson;
import com.luckysite.config.PublicApiConfig;
import com.luckysite.dto.publicApi.DreamAnalyticalDTO;
import com.luckysite.dto.publicApi.DreamAnalyticalDetailsDTO;
import com.luckysite.model.publicApi.DreamAnalyticalModel;
import com.luckysite.model.publicApi.DreamDetailsModel;
import com.luckysite.service.PublicApiService;
import com.luckysite.util.HttpUtil;
import com.luckysite.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicApiServiceImpl implements PublicApiService {

    private Logger log = LoggerFactory.getLogger(PublicApiServiceImpl.class);

    //成功时远程服务器返回标志位
    private static final String SUCCESSED = "successed";

    //成功时远程服务器返回标志位键值
    private static final String REASON = "reason";

    //返回的数据
    private static final String RESULT = "result";

    @Resource
    private PublicApiConfig publicApiConfig;

    @Override
    public ResponseResult<DreamAnalyticalDTO> getDreamAnalytical(String q, int full) {
        ResponseResult responseResult = new ResponseResult();
        DreamAnalyticalDTO dreamAnalyticalDTO = new DreamAnalyticalDTO();
        List<DreamAnalyticalDetailsDTO> dreamAnalyticalDetails = new ArrayList<>();

        StringBuilder getUrlBuilder = new StringBuilder();
        getUrlBuilder.append(publicApiConfig.getUrl());
        getUrlBuilder.append("?");
        getUrlBuilder.append("q=" + q);
        getUrlBuilder.append("&");
        getUrlBuilder.append("full=" + full);
        getUrlBuilder.append("&");
        getUrlBuilder.append("key=" + publicApiConfig.getKey());

        String rtnMsg = HttpUtil.doGet(getUrlBuilder.toString());
        log.info("PublicApiServiceImpl-getDreamAnalytical-远程调用返回值为：" + rtnMsg);

        //转换成为对象
        DreamAnalyticalModel dreamAnalyticalModel = new Gson().fromJson(rtnMsg, DreamAnalyticalModel.class);

        if (dreamAnalyticalModel.getReason().equals(SUCCESSED)) {
            List<DreamDetailsModel> dreamAnalyticalModelResult = dreamAnalyticalModel.getResult();
            for (DreamDetailsModel dreamDetailsModel : dreamAnalyticalModelResult) {
                DreamAnalyticalDetailsDTO dreamAnalyticalDetailsDTO = new DreamAnalyticalDetailsDTO();
                dreamAnalyticalDetailsDTO.setTitle(dreamDetailsModel.getTitle());
                dreamAnalyticalDetailsDTO.setDes(dreamDetailsModel.getDes());
                dreamAnalyticalDetailsDTO.setList(dreamDetailsModel.getList());
                dreamAnalyticalDetails.add(dreamAnalyticalDetailsDTO);
            }
            dreamAnalyticalDTO.setDreamAnalyticalDetails(dreamAnalyticalDetails);
        }

        return responseResult.success(dreamAnalyticalDTO);
    }
}
