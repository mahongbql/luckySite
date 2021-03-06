package com.luckysite.common.Interceptors;

import com.luckysite.common.annotation.Auth;
import com.luckysite.dto.login.LoginDataDTO;
import com.luckysite.entity.User;
import com.luckysite.service.UserService;
import com.luckysite.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.lang.reflect.Method;

@Slf4j
@Component
public class AccessHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 无论controller中是否抛出异常，都会调用该方法
     * @param request
     * @param response
     * @param obj
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
        if(null != ex){
            log.error("ex is " + ex);
        }
    }

    /**
     * 如果controller中抛出异常，则该方法不会被调用
     * @param request
     * @param response
     * @param obj
     * @param view
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView view) {}

    /**
     * 最先执行该方法
     * @param request
     * @param response
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        if (obj instanceof HandlerMethod) {
            String token = getToken(request);
            String methodName = ((HandlerMethod)obj).getMethod().getName();

            log.info("methodName -> {}", methodName);
            log.info("获取到前端的token -> {}", token);

            Object userObj = redisUtil.get(token);
            if(null == userObj){
                log.error("AccessHandlerInterceptor-token失效");
                return false;
            }
            LoginDataDTO loginDataDTO = (LoginDataDTO)userObj;
            if(null == loginDataDTO.getUserId()) {
                log.error("AccessHandlerInterceptor: userId is null -> {}", loginDataDTO);
                endTime = System.currentTimeMillis();
                log.info("拦截器耗费时间：{}s", (endTime - startTime)/1000);
                return false;
            }

            Method[] methods = ((HandlerMethod)obj).getBean().getClass().getMethods();

            for(Method method : methods) {
                // 如果此方法有注解，就把注解里面的数据赋值到user对象
                if (method.isAnnotationPresent(Auth.class)){
                    if(methodName.equals(method.getName())) {
                        Auth auth = method.getAnnotation(Auth.class);
                        log.info("AccessHandlerInterceptor-preHandle-用户 " + loginDataDTO.getUserId() + " ，auth: " + auth.role());

                        int uAuth = loginDataDTO.getRole();
                        if(uAuth >= auth.role()){
                            log.info("AccessHandlerInterceptor-preHandle-用户 " + loginDataDTO.getUserId() + "权限通过");
                            endTime = System.currentTimeMillis();
                            log.info("拦截器耗费时间：{}s", (endTime - startTime)/1000);
                            return true;
                        }

                        log.info("AccessHandlerInterceptor-preHandle-用户 " + loginDataDTO.getUserId() + "无权限访问");
                        endTime = System.currentTimeMillis();
                        log.info("拦截器耗费时间：{}s", (endTime - startTime)/1000);
                        return false;
                    }
                }
            }
        }

        log.info("拦截器耗费时间：{}s", (endTime - startTime)/1000);
        return true;
    }

    /**
     * 获取前端参数的token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request){
        String token = request.getParameter("token");

        if(null == token){
            StringBuffer jb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                }

                if(jb.toString().isEmpty()){
                    return null;
                }
                token = JSONObject.fromObject(jb.toString()).getString("token");
                log.info("token: " + token + " ----------------");
            } catch (Exception e) {
                token = null;
                log.error("AccessHandlerInterceptor-getToken-token转换失败: " + e);
            }
        }
        return token;
    }
}
