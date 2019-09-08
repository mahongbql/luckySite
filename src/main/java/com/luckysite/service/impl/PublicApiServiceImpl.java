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
import com.luckysite.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PublicApiServiceImpl implements PublicApiService {

    //成功时远程服务器返回标志位
    private static final String SUCCESSED = "successed";

    @Resource
    private PublicApiConfig publicApiConfig;

    /**
     * 进行远程访问
     */
    private static RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseResult<DreamAnalyticalDTO> getDreamAnalytical(String q, int full) {
        ResponseResult responseResult = new ResponseResult();
        DreamAnalyticalDTO dreamAnalyticalDTO = new DreamAnalyticalDTO();
        List<DreamAnalyticalDetailsDTO> dreamAnalyticalDetails = new ArrayList<>();

        //转换成为对象
        Map<String, Object> params = new HashMap();
        params.put("q", q);
        params.put("full", full);
        params.put("key", publicApiConfig.getDreamKey());
        ResponseEntity<DreamAnalyticalModel> entity = restTemplate.getForEntity(publicApiConfig.getDreamUrl(), DreamAnalyticalModel.class, params);

        if (entity.getStatusCode().value() == HttpStatus.OK.value()) {
            if (entity.getBody().getReason().equals(SUCCESSED)) {
                List<DreamDetailsModel> dreamAnalyticalModelResult = entity.getBody().getResult();
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
        } else {
            log.error("getDreamAnalytical-错误日志：" + entity.getBody());
            return responseResult.fail("数据获取失败");
        }
    }

    @Override
    public ResponseResult<LaoHuangLiDTO> getLaoHuangLi(String date) {
        ResponseResult responseResult = new ResponseResult();

        //转换成为对象
        Map<String, String> params = new HashMap();
        params.put("date", date);
        params.put("key", publicApiConfig.getLaohuangliKey());
        ResponseEntity<LaoHuangLiModel> entity = restTemplate.getForEntity(publicApiConfig.getLaohuangliUrl(), LaoHuangLiModel.class, params);
        log.info("" + entity.getStatusCodeValue());

        if (entity.getStatusCode().value() == HttpStatus.OK.value()) {
            LaoHuangLiDTO laoHuangLiDTO = new LaoHuangLiDTO();
            if (entity.getBody().getReason().equals(SUCCESSED)) {
                LaoHuangLiDetailsModel laoHuangLiDetailsModel = entity.getBody().getResult();
                BeanUtils.copyProperties(laoHuangLiDetailsModel, laoHuangLiDTO);
            }

            return responseResult.success(laoHuangLiDTO);
        } else {
            log.error("getLaoHuangLi-错误日志：" + entity.getBody());
            return responseResult.fail("数据获取失败");
        }

    }
}
