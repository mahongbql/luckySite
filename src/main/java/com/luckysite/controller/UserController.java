package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.config.AppConfig;
import com.luckysite.config.AuthConfig;
import com.luckysite.enmu.ResultCode;
import com.luckysite.enmu.UserStatusEnmu;
import com.luckysite.enmu.UserTypeEnmu;
import com.luckysite.entity.Pic;
import com.luckysite.entity.User;
import com.luckysite.model.PicParamModel;
import com.luckysite.model.Result;
import com.luckysite.model.UserDataModel;
import com.luckysite.service.GetImageService;
import com.luckysite.service.UserService;
import com.luckysite.util.HttpUtil;
import com.luckysite.util.RedisUtil;
import com.luckysite.util.ResultUtil;
import com.luckysite.util.TimeUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RedisUtil redisUtil;

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
            log.error("Exception is " + ex);
        }

        return user;
    }

    /**
     * 用户登录
     * @param resCode
     * @param httpSession
     * @return
     */
    @RequestMapping("/login")
    public @ResponseBody
    Result login(@RequestParam("resCode") String resCode, HttpSession httpSession){
        log.info("user-login-用户resCode: " + resCode);

        String appId = appConfig.getAppId();
        String appSecret = appConfig.getAppSecret();

        String url = appConfig.getUrl()+"&appid=" + appId + "&secret=" + appSecret + "&js_code=" + resCode;
        log.info("user-login-远程访问url：" + url);

        String jsonStr = HttpUtil.doGet(url);
        log.info("user-login-返回参数：" + jsonStr);

        JSON json = JSONObject.fromObject(jsonStr);
        String sessionKey = ((JSONObject) json).getString("session_key");
        String openid = ((JSONObject) json).getString("openid");

        log.info("user-login-sessionKey：" + sessionKey);
        log.info("user-login-openid：" + openid);

        User user = userService.getByUserName(openid);

        if(null == user){
            log.error("user-login-用户进行注册：用户 " + openid);
            user = new User();
            user.setUserName(openid);
            user = register(user);

            if(null == user){
                log.error("user-login-注册失败：用户 " + openid);
                return ResultUtil.error(ResultCode.ERROR.getCode(), "用户注册异常", null);
            }
        }

        log.info("user-login：用户 " + openid + " 登陆成功");
        httpSession.setAttribute(sessionKey, user);

        user.setToken(sessionKey);
        userService.updateLoginInfo(user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("token", sessionKey);
        result.put("userId", user.getUserId());
        result.put("role", user.getRole());
        result.put("lastLoginTime", TimeUtil.transFormDate(user.getLoginTime()));

        return ResultUtil.success(result);
    }

    /**
     * 获取图片信息列表
     * @param userDataModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/getDataList")
    @ResponseBody
    public Result getDataList(UserDataModel userDataModel){
        Map<String, Object> result = new HashMap<>();
        int type = userDataModel.getType();
        log.info("UserController-getDataList-类型：" + (type == 1 ? "获取图片" : "获取文章"));

        switch (type) {
            case 0:
                result.put("data", null);
                break;
            case 1:
                List<Pic> dataList = userService.getImage(userDataModel);
                result.put("data", dataList);
                break;
        }

        return ResultUtil.success(result);
    }

    /**
     * vip升级
     * @param userDataModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/upVipLevel")
    @ResponseBody
    public Result upVipLevel(UserDataModel userDataModel){
        Long userId = Long.parseLong(userDataModel.getUserId());

        if(userService.haveApplied(userId)){
            return ResultUtil.error(ResultCode.ERROR.getCode(),"审核中", null);
        }

        if (userService.upVipLevel(userId)){
            return ResultUtil.error(ResultCode.ERROR.getCode(),"用户已经是最高级", null);
        }

        return ResultUtil.success();
    }

    /**
     * 完善用户信息
     * @param userDataModel
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/perfectUserInfo")
    @ResponseBody
    public Result perfectUserInfo(UserDataModel userDataModel){
        userService.perfectUserInfo(userDataModel);

        return ResultUtil.success();
    }
}
