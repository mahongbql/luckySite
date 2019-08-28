package com.luckysite.publicApiService;

import com.luckysite.LuckysiteApplicationTests;
import com.luckysite.dto.DreamAnalyticalDTO;
import com.luckysite.service.PublicApiService;
import com.luckysite.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author mahongbin
 * @date 2019/8/27 10:17
 * @Description
 */
@Slf4j
public class PublicApiServiceTest extends LuckysiteApplicationTests {

    @Resource
    private PublicApiService publicApiService;

    @Test
    public void testGetDreamAnalytical() {
        ResponseResult<DreamAnalyticalDTO> responseResult = publicApiService.getDreamAnalytical("出租车", 1);
        log.info(JSONObject.fromObject(responseResult).toString());
    }
}
