package com.luckysite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RobotConfig {
    @Value("${robot.userId}")
    private String userId;

    @Value("${robot.apikey}")
    private String apiKey;

    public String getUserId() {
        return this.userId;
    }

    public String getApiKey() {
        return this.apiKey;
    }
}
