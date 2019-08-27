package com.luckysite.service.impl;

import com.luckysite.config.PublicApiConfig;
import com.luckysite.dto.DreamAnalyticalDTO;
import com.luckysite.service.PublicApiService;
import com.luckysite.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    public DreamAnalyticalDTO getDreamAnalytical(String q, int full) {
        DreamAnalyticalDTO dreamAnalyticalDTO = new DreamAnalyticalDTO();
        List<String> list = new ArrayList<>();
        String title = "无解";
        String des = "你这梦太难了，周公解不开... ...";

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

        JSONObject objJson = JSONObject.fromObject(rtnMsg);
        if (objJson.getString(REASON).equalsIgnoreCase(SUCCESSED)) {
            Object o = objJson.getString(RESULT);
            if (!o.toString().equals("null")) {
                JSONArray jsonArray = JSONArray.fromObject(objJson.getString(RESULT));
                for (int i = 0; i < jsonArray.size(); i++) {
                    title = jsonArray.getJSONObject(i).getString("title");
                    des = jsonArray.getJSONObject(i).getString("des");
                    JSONArray array = JSONArray.fromObject(jsonArray.getJSONObject(i).getString("list"));
                    for (int j = 0; j < array.size(); j++) {
                        list.add(array.getString(j));
                    }
                }
            }
        }

        dreamAnalyticalDTO.setDes(des);
        dreamAnalyticalDTO.setTitle(title);
        dreamAnalyticalDTO.setList(list);
        return dreamAnalyticalDTO;
    }
}
