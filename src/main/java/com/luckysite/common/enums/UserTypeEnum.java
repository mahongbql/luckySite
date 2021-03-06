package com.luckysite.common.enums;

import com.luckysite.config.AuthConfig;
import lombok.Getter;

@Getter
public enum UserTypeEnum {
    /**
     * 用户角色
     */
    USER("user", AuthConfig.USER),

    /**
     * vip角色
     */
    VIP("vip", AuthConfig.VIP),

    /**
     * 认证作者
     */
    AUTHOR("author", AuthConfig.AUTHOR),

    /**
     * 管理员
     */
    ADMIN("admin", AuthConfig.ADMIN),

    ;

    private String role;
    private int roleId;

    UserTypeEnum(String role, int roleId) {
        this.role = role;
        this.roleId = roleId;
    }
}
