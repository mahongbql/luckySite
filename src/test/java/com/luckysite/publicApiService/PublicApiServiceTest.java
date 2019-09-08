package com.luckysite.publicApiService;

import com.luckysite.LuckysiteApplicationTests;
import com.luckysite.dto.publicApi.DreamAnalyticalDTO;
import com.luckysite.dto.publicApi.LaoHuangLiDTO;
import com.luckysite.service.PublicApiService;
import com.luckysite.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
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
        ResponseResult<DreamAnalyticalDTO> responseResult = publicApiService.getDreamAnalytical("大海", 1);
        log.info(JSONObject.fromObject(responseResult).toString());
    }

    @Test
    public void testGetLaoHuangLi() {
        ResponseResult<LaoHuangLiDTO> responseResult = publicApiService.getLaoHuangLi("2019-08-28");
        log.info(JSONObject.fromObject(responseResult).toString());
    }

}
