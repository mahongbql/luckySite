package com.luckysite.config;

import com.luckysite.common.Interceptors.AccessHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author mahongbin
 * @date 2019/8/27 15:23
 * @Description
 */
@Slf4j
@Configuration
public class MvcConfigurerConfig implements WebMvcConfigurer {

    @Autowired
    private AccessHandlerInterceptor accessHandlerInterceptor;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.CHINESE);
        return slr;
    }

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
        excludePathList.add("/webjars/springfox-swagger-ui/**");

        //注册日志拦截器
        registry.addInterceptor(accessHandlerInterceptor)
                //添加需要拦截的路径
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathList);
    }
}
