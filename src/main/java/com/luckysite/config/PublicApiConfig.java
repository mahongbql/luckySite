package com.luckysite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PublicApiConfig {

    @Value("${publicApi.url}")
    private String url;

    @Value("${publicApi.key}")
    private String key;

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }
}
