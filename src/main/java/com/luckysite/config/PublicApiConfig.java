package com.luckysite.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PublicApiConfig {

    @Value("${publicApi.dream_url}")
    private String dreamUrl;

    @Value("${publicApi.dream_key}")
    private String dreamKey;

    @Value("${publicApi.laohuangli_url}")
    private String laohuangliUrl;

    @Value("${publicApi.laohuangli_key}")
    private String laohuangliKey;
}
