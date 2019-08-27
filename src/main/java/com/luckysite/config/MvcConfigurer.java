package com.luckysite.config;

import com.luckysite.common.Interceptors.AccessHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    /**
     * 拦截请求
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/login");
        excludePathList.add("/error");
        excludePathList.add("/swagger/**");
        excludePathList.add("/swagger-ui.html");
        excludePathList.add("/swagger-resources/**");
        excludePathList.add("/v2/**");

        //注册日志拦截器
        registry.addInterceptor(accessHandlerInterceptor)
                //添加需要拦截的路径
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathList);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
