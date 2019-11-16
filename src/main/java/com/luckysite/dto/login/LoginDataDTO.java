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

    private Byte post;

    private Byte pic;

    private Byte dream;

    private Byte calender;
}
