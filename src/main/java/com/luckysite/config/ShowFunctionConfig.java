package com.luckysite.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mahongbin
 * @date 2019/11/15 15:38
 * 具体需要展示那些功能
 */
@Getter
@Component
public class ShowFunctionConfig {

    @Value("${showFunction.post}")
    private Integer post;

    @Value("${showFunction.pic}")
    private Integer pic;

    @Value("${showFunction.dream}")
    private Integer dream;

    @Value("${showFunction.calender}")
    private Integer calender;

}
