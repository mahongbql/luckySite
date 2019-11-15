package com.luckysite.dto.login;

import lombok.Data;

/**
 * @author mahongbin
 * @date 2019/11/15 15:43
 * 登录参数
 */
@Data
public class LoginDataDTO {

    private String token;

    private Long userId;

    private Integer role;

    private String lastLoginTime;

    private Integer post;

    private Integer pic;

    private Integer dream;

    private Integer calender;
}
