package com.luckysite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlListConfig {

    @Value("${urlList}")
    private String urlList;

    public String getUrlList() {
        return this.urlList;
    }
}
