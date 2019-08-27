package com.luckysite.common.Interceptors;

import com.luckysite.common.annotation.Auth;
import com.luckysite.entity.User;
import com.luckysite.service.UserService;
import com.luckysite.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
@Component
public class AccessHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    //无论controller中是否抛出异常，都会调用该方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex)
            throws Exception {
        if(null != ex){
            log.error("ex is " + ex);
        }
    }

    //如果controller中抛出异常，则该方法不会被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView view)
            throws Exception {
    }

    //最先执行该方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        if (obj instanceof HandlerMethod) {
            String token = getToken(request);
            String methodName = ((HandlerMethod)obj).getMethod().getName();

            Object userObj = redisUtil.get(token);
            if(null == userObj){
                log.error("AccessHandlerInterceptor-token失效");
                return false;
            }
            JSONObject userJson = JSONObject.fromObject(userObj);
            User user = userService.getByUserId(Integer.parseInt(userJson.get("userId").toString()));

            Method[] methods = ((HandlerMethod)obj).getBean().getClass().getMethods();

            for(Method method : methods) {
                // 如果此方法有注解，就把注解里面的数据赋值到user对象
                if (method.isAnnotationPresent(Auth.class)){
                    if(methodName.equals(method.getName())) {
                        Auth auth = method.getAnnotation(Auth.class);
                        log.info("AccessHandlerInterceptor-preHandle-用户 " + user.getUserName() + " ，auth: " + auth.role());

                        int uAuth = user.getRole();
                        if(uAuth >= auth.role()){
                            log.info("AccessHandlerInterceptor-preHandle-用户 " + user.getUserName() + "权限通过");
                            return true;
                        }

                        log.info("AccessHandlerInterceptor-preHandle-用户 " + user.getUserName() + "无权限访问");

                        return false;
                    }
                }
            }
        }

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
