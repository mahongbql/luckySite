package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.common.enums.LuckySiteErrorEnum;
import com.luckysite.config.AppConfig;
import com.luckysite.config.AuthConfig;
import com.luckysite.config.ShowFunctionConfig;
import com.luckysite.dto.login.LoginDataDTO;
import com.luckysite.common.enums.ResultCode;
import com.luckysite.common.enums.UserStatusEnum;
import com.luckysite.common.enums.UserTypeEnum;
import com.luckysite.entity.Pic;
import com.luckysite.entity.Post;
import com.luckysite.entity.User;
import com.luckysite.model.Result;
import com.luckysite.model.UserDataModel;
import com.luckysite.service.CacheService;
import com.luckysite.service.RobotService;
import com.luckysite.service.UserService;
import com.luckysite.util.*;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * token失效时间设定为半小时
     */
    private static final Integer EXPIRE_TIME = 60*30;

    @Autowired
    private UserService userService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RobotService robotService;

    @Autowired
    private ShowFunctionConfig showFunctionConfig;

    /**
     * 用户注册
     * @param user
     */
    public User register(User user){
        try {
            user.setFreez(UserStatusEnum.NOT_FREEZ.getStatus());
            user.setRole(UserTypeEnum.USER.getRoleId());
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
     * @return
     */
    @RequestMapping("/login")
    public @ResponseBody ResponseResult<LoginDataDTO> login(@RequestParam("resCode") String resCode){
        ResponseResult<LoginDataDTO> responseResult = new ResponseResult<>();
        LoginDataDTO loginDataDTO = new LoginDataDTO();
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
                return responseResult.fail(LuckySiteErrorEnum.REGISTER_ERROR.getResponseMessage());
            }
        }

        log.info("user-login：用户 " + openid + " 登陆成功");
        userService.updateLoginInfo(user);

        loginDataDTO.setLastLoginTime(TimeUtil.transFormDate(user.getLoginTime()));
        loginDataDTO.setRole(user.getRole());
        loginDataDTO.setToken(sessionKey);
        loginDataDTO.setUserId(user.getUserId());
        loginDataDTO.setPost(showFunctionConfig.getPost());
        loginDataDTO.setPic(showFunctionConfig.getPic());
        loginDataDTO.setDream(showFunctionConfig.getDream());
        loginDataDTO.setCalender(showFunctionConfig.getCalender());

        redisUtil.set(sessionKey, loginDataDTO, EXPIRE_TIME);

        return responseResult.success(loginDataDTO);
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
                List<Post> postList = userService.getPosts(userDataModel);
                for(Post post : postList){
                    post.setViewNumber(cacheService.getViewNumber(CacheKeyUtil.POST_VIEW_NUMBER, post.getId().toString()));
                    post.setCollectNumber(cacheService.getCollectNumber(CacheKeyUtil.POST_COLLECT_NUMBER, post.getId().toString()));
                }
                result.put("data", postList);
                break;
            case 1:
                List<Pic> dataList = userService.getImage(userDataModel);
                result.put("data", dataList);
                break;
            case 2:
                result.put("data", null);
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

    /**
     * 机器人对话
     * @param msg
     * @return
     */
    @Auth(role = AuthConfig.USER)
    @RequestMapping("/robotTalk")
    @ResponseBody
    public Result robotTalk(@Param("msg") String msg){
        Map<String, Object> robotMsg = new HashMap<>();
        robotMsg.put("rtnMsg", robotService.sendMsg(msg));

        return ResultUtil.success(robotMsg);
    }
}
