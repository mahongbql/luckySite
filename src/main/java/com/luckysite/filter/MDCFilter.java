package com.luckysite.filter;

import com.luckysite.util.HttpBodyReaderWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mahongbin
 * @date 2019/6/20 14:04
 * @Description
 */
@Slf4j
@WebFilter(filterName = "mdcFilter", urlPatterns = "/*")
@Component
public class MDCFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest newRequest = null;

        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            String hostAddress = getLocalIp(request);

            String api = request.getRequestURI();

            log.info("filter message hostAddress：" + hostAddress);

//            MDC.put("context.api", api);
//            MDC.put("context.ip", hostAddress);

            // http请求参数输出到索引
            MDC.put("context.method", request.getMethod());
            if ("GET".equals(request.getMethod())) {
                Map<String, String> parameters = new HashMap<>(16);
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String name = parameterNames.nextElement();
                    String value = request.getParameter(name);
//                    parameters.put(name, value);
                }
                MDC.put("context.parameters", parameters.toString());

            } else if ("POST".equals(request.getMethod())) {
                newRequest = new HttpBodyReaderWrapper((HttpServletRequest) servletRequest);
                String body = (new HttpBodyReaderWrapper(request)).getBody();
//                MDC.put("context.parameters", body);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            if(newRequest == null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(newRequest, servletResponse);
            }
            MDC.clear();
        }
    }

    /**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

}
