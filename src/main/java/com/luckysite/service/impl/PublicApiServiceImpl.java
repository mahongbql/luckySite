package com.luckysite.service.impl;

import com.google.gson.Gson;
import com.luckysite.config.PublicApiConfig;
import com.luckysite.dto.publicApi.DreamAnalyticalDTO;
import com.luckysite.dto.publicApi.DreamAnalyticalDetailsDTO;
import com.luckysite.dto.publicApi.LaoHuangLiDTO;
import com.luckysite.model.publicApi.DreamAnalyticalModel;
import com.luckysite.model.publicApi.DreamDetailsModel;
import com.luckysite.model.publicApi.LaoHuangLiDetailsModel;
import com.luckysite.model.publicApi.LaoHuangLiModel;
import com.luckysite.service.PublicApiService;
import com.luckysite.util.HttpUtil;
import com.luckysite.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicApiServiceImpl implements PublicApiService {

    private Logger log = LoggerFactory.getLogger(PublicApiServiceImpl.class);

    //成功时远程服务器返回标志位
    private static final String SUCCESSED = "successed";

    @Resource
    private PublicApiConfig publicApiConfig;

    @Override
    public ResponseResult<DreamAnalyticalDTO> getDreamAnalytical(String q, int full) {
        ResponseResult responseResult = new ResponseResult();
        DreamAnalyticalDTO dreamAnalyticalDTO = new DreamAnalyticalDTO();
        List<DreamAnalyticalDetailsDTO> dreamAnalyticalDetails = new ArrayList<>();

        StringBuilder getUrlBuilder = new StringBuilder();
        getUrlBuilder.append(publicApiConfig.getDreamUrl());
        getUrlBuilder.append("?q=");
        getUrlBuilder.append(q);
        getUrlBuilder.append("&full=");
        getUrlBuilder.append(full);
        getUrlBuilder.append("&key=");
        getUrlBuilder.append(publicApiConfig.getDreamKey());

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

    @Override
    public ResponseResult<LaoHuangLiDTO> getLaoHuangLi(String date) {
        ResponseResult responseResult = new ResponseResult();
        StringBuilder getUrlBuilder = new StringBuilder();
        getUrlBuilder.append(publicApiConfig.getLaohuangliUrl());
        getUrlBuilder.append("?date=");
        getUrlBuilder.append(date);
        getUrlBuilder.append("&key=");
        getUrlBuilder.append(publicApiConfig.getLaohuangliKey());

        String rtnMsg = HttpUtil.doGet(getUrlBuilder.toString());
        log.info("PublicApiServiceImpl-getLaoHuangLi-远程调用返回值为：" + rtnMsg);

        //转换成为对象
        LaoHuangLiModel laoHuangLiModel = new Gson().fromJson(rtnMsg, LaoHuangLiModel.class);

        LaoHuangLiDTO laoHuangLiDTO = new LaoHuangLiDTO();
        if (laoHuangLiModel.getReason().equals(SUCCESSED)) {
            LaoHuangLiDetailsModel laoHuangLiDetailsModel = laoHuangLiModel.getResult();
            BeanUtils.copyProperties(laoHuangLiDetailsModel, laoHuangLiDTO);
        }

        return responseResult.success(laoHuangLiDTO);
    }
}
