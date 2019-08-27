package com.luckysite.config;

import com.luckysite.common.Interceptors.AccessHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mahongbin
 * @date 2019/8/27 15:23
 * @Description
 */
@Slf4j
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Resource
    private AccessHandlerInterceptor accessHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/static/**");
        excludePathList.add("/login");
        excludePathList.add("/error");
        excludePathList.add("/swagger/**");
        excludePathList.add("/swagger-ui.html");
        excludePathList.add("/swagger-resources/**");
        excludePathList.add("/v2/**");
        excludePathList.add("/webjars/springfox-swagger-ui/**");
        registry.addInterceptor(accessHandlerInterceptor)
                .excludePathPatterns(excludePathList)
                .addPathPatterns("/**");
    }
}
