package com.luckysite.service.impl;

import com.luckysite.config.RobotConfig;
import com.luckysite.service.RobotService;
import com.luckysite.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotServiceImpl implements RobotService {

    private Logger log = LoggerFactory.getLogger(RobotServiceImpl.class);

    @Autowired
    private RobotConfig robotConfig;

    @Override
    public String sendMsg(String msg) {
        String userId = robotConfig.getUserId();
        String apiKey = robotConfig.getApiKey();
        String url = robotConfig.getUrl();

        String data = "{ " +
                "           \"reqType\":0, " +
                "           \"perception\": { " +
                "                   \"inputText\": { \"text\": '" + msg + " '} " +
                "             }, " +
                "            \"userInfo\": " +
                "                { \"apiKey\": '" + apiKey + "', \"userId\": '" + userId + "'} " +
                "}";

        String rtnMsg = "唉，嗓子坏了，说不出话 。。。";

        try{
            rtnMsg = HttpUtil.doPost(url, data);
            JSONObject jo = JSONObject.fromObject(rtnMsg);
            JSONArray jsonArray = jo.getJSONArray("results");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            jo = JSONObject.fromObject(jsonObject.getString("values"));
            rtnMsg = jo.getString("text");
        }catch (Exception e){
            log.error("RobotServiceImpl-sendMsg :" + e);
        }

        return rtnMsg;
    }
}
