package com.luckysite.common.Interceptors;

import com.luckysite.common.annotation.Auth;
import com.luckysite.entity.User;
import com.luckysite.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

@Component
public class AccessHandlerInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(AccessHandlerInterceptor.class);

    @Autowired
    private UserService userService;

    //无论controller中是否抛出异常，都会调用该方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex)
            throws Exception {
        log.info("AccessHandlerInterceptor-afterCompletion-开始执行");
        long start=new Date().getTime();
        log.info("AccessHandlerInterceptor-afterCompletion-最后耗时为:"+(new Date().getTime() - start));
        log.error("ex is " + ex);
    }

    //如果controller中抛出异常，则该方法不会被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView view)
            throws Exception {
        log.info("AccessHandlerInterceptor-postHandle-方法执行成功");
    }

    //最先执行该方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        log.info("AccessHandlerInterceptor-preHandle-开始执行");

        int userId = Integer.parseInt(request.getParameter("userId").toString());
        log.info("AccessHandlerInterceptor-preHandle-userId: " + userId);

        String ip = request.getRemoteHost();
        log.info("AccessHandlerInterceptor-preHandle-request ip: " + ip);

        String methodName = ((HandlerMethod)obj).getMethod().getName();

        Method[] methods = ((HandlerMethod)obj).getBean().getClass().getMethods();

        for(Method method : methods) {
            // 如果此方法有注解，就把注解里面的数据赋值到user对象
            if (method.isAnnotationPresent(Auth.class)){
                if(methodName.equals(method.getName())) {
                    Auth auth = method.getAnnotation(Auth.class);
                    log.info("AccessHandlerInterceptor-preHandle-用户 " + userId + " ，auth: " + auth.role());

                    User user = userService.get(userId);
                    int uAuth = user.getRole();

                    if(uAuth >= auth.role()){
                        log.info("AccessHandlerInterceptor-preHandle-用户 " + user.getUserName() + "权限通过");
                        return true;
                    }

                    log.info("AccessHandlerInterceptor-preHandle-用户 " + user.getUserName() + "无权限访问");
                }
            }
        }

        return false;
    }
}
