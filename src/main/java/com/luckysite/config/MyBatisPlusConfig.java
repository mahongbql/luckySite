package com.luckysite.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mahongbin
 * @date 2019/6/20 11:54
 * @Description
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.luckysite.mapper")
public class MyBatisPlusConfig {
    @Bean // mybatis-plus分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
