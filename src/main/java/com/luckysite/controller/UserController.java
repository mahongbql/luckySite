package com.luckysite.controller;

import com.luckysite.enmu.ResultCode;
import com.luckysite.enmu.UserStatusEnmu;
import com.luckysite.enmu.UserTypeEnmu;
import com.luckysite.entity.User;
import com.luckysite.model.Result;
import com.luckysite.service.UserService;
import com.luckysite.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     */
    public User register(User user){
        try {
            user.setFreez(UserStatusEnmu.NOT_FREEZ.getStatus());
            user.setRole(UserTypeEnmu.USER.getRoleId());
            user.setLoginTime(new Date());
            user.setRefreshTime(new Date());
            user.setRegisterTime(new Date());
            userService.insertUser(user);
            log.info("user-register-用户注册：用户信息存储完毕");
        }catch (Exception ex) {
            log.error("user-register-用户注册：用户 " + user.getUserName() + " 信息存储异常，注册失败");
        }

        return user;
    }

    /**
     * 用户登录
     * @param loginUser
     */
    @RequestMapping("login")
    public Result login(@RequestParam User loginUser, HttpSession httpSession){
        String userName = loginUser.getUserName();
        log.info("user-login：用户 " + userName + "进行登录验证");

        User user = userService.get(userName);

        if(null == user){
            log.error("user-login-用户进行注册：用户 " + loginUser.getUserName());
            user.setUserName(userName);
            user = register(user);
        }

        if(null == user){
            log.error("user-login-注册失败：用户 " + loginUser.getUserName());
            return ResultUtil.error(ResultCode.ERROR.getCode(), "用户注册异常", null);
        }

        log.info("user-login：用户 " + user.getUserName() + " 登陆成功");
        httpSession.setAttribute(user.getUserId()+"", user);

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("url", "/page/index/index");

        return ResultUtil.success(result);
    }
}
