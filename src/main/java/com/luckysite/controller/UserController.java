package com.luckysite.controller;

import com.luckysite.common.annotation.Auth;
import com.luckysite.common.constant.LuckySiteConstant;
import com.luckysite.common.enums.LuckySiteErrorEnum;
import com.luckysite.config.AppConfig;
import com.luckysite.config.AuthConfig;
import com.luckysite.dto.login.LoginDataDTO;
import com.luckysite.common.enums.ResultCode;
import com.luckysite.common.enums.UserStatusEnum;
import com.luckysite.common.enums.UserTypeEnum;
import com.luckysite.entity.FunctionShow;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

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
    @Qualifier(value = "crawlExecutorPool")
    private ExecutorService pool;

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

        Callable<LoginDataDTO> callable = new Callable<LoginDataDTO>() {
            @Override
            public LoginDataDTO call() throws Exception {
                LoginDataDTO loginDataDTO = new LoginDataDTO();
                log.info("user-login-用户resCode: " + resCode);

                String appId = appConfig.getAppId();
                String appSecret = appConfig.getAppSecret();

                StringBuffer sb = new StringBuffer();
                sb.append(appConfig.getUrl());
                sb.append("&appid=");
                sb.append(appId);
                sb.append("&secret=");
                sb.append(appSecret);
                sb.append("&js_code=");
                sb.append(resCode);
                String url = sb.toString();
                log.info("user-login-远程访问url：" + url);

                Long startTimeHttp = System.currentTimeMillis();
                String jsonStr = HttpUtil.doGet(url);
                Long endTimeHttp = System.currentTimeMillis();
                log.info("user-login-返回参数：{}, 耗费时间：{} s", jsonStr, (endTimeHttp-startTimeHttp)/1000);

                JSON json = JSONObject.fromObject(jsonStr);
                String sessionKey = ((JSONObject) json).getString("session_key");
                String openid = ((JSONObject) json).getString("openid");

                log.info("user-login-sessionKey：" + sessionKey);
                log.info("user-login-openid：" + openid);

//                Long startTimeDb = System.currentTimeMillis();
//                User user = userService.getByUserName(openid);
//                Long endTimeDb = System.currentTimeMillis();
//                log.info("用户登陆数据库查询耗费时间：{} s", (endTimeDb-startTimeDb)/1000);
//
//                if(null == user){
//                    log.error("user-login-用户进行注册：用户 " + openid);
//                    user = new User();
//                    user.setUserName(openid);
//                    user = register(user);
//
//                    if(null == user){
//                        log.error("user-login-注册失败：用户 " + openid);
//                        throw new RuntimeException(LuckySiteErrorEnum.REGISTER_ERROR.getResponseMessage());
//                    }
//
//                    //新注册的用户没有用户id
//                    user = userService.getByUserName(user.getUserName());
//                }
//
//                log.info("user-login：用户 " + openid + " 登陆成功");
//                userService.updateLoginInfo(user);

                Date now = new Date();
                loginDataDTO.setLastLoginTime(TimeUtil.transFormDate(now));
                loginDataDTO.setRole(openid.equals("oYd_946wGPxN1TNCgUlNIoPvH4Gg")?10:1);
                loginDataDTO.setToken(sessionKey);
                loginDataDTO.setUserId(1L);

                //设置小程序端显示哪些数据
                setShowFunction(loginDataDTO);

                startTimeHttp = System.currentTimeMillis();
                redisUtil.set(sessionKey, loginDataDTO, LuckySiteConstant.EXPIRE_TIME);
                endTimeHttp = System.currentTimeMillis();
                log.info("设置缓存耗费时间：{} s", (endTimeHttp-startTimeHttp)/1000);


                log.info("获取到的用户登录返回数据为 loginDataDTO：{}", loginDataDTO);

                return loginDataDTO;
            }
        };

        FutureTask<LoginDataDTO> userTask = new FutureTask<LoginDataDTO>(callable);
        pool.submit(userTask);

        try {
            responseResult.setData(userTask.get());
        } catch (Exception e) {
            return responseResult.fail(e.getMessage());
        }

        return responseResult;
    }

    /**
     * 设置小程序端显示哪些数据
     * @param loginDataDTO
     */
    private void setShowFunction(LoginDataDTO loginDataDTO) {
//        List<FunctionShow> functionShowList = userService.getFunctionShow();
//        Map<String, Byte> showMap =
//                functionShowList.stream().collect(Collectors.toMap(FunctionShow::getFunctionType, FunctionShow::getIsShow));
        loginDataDTO.setPost((byte)1);
        loginDataDTO.setPic((byte)1);
        loginDataDTO.setDream((byte)1);
        loginDataDTO.setCalender((byte)1);
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
