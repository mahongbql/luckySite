package com.luckysite.filter;

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

            InetAddress address = InetAddress.getLocalHost();
            String hostAddress = address.getHostAddress();

            String api = request.getRequestURI();

            MDC.put("context.api", api);
            MDC.put("context.ip", hostAddress);

            // http请求参数输出到索引
            MDC.put("context.method", request.getMethod());
            if ("GET".equals(request.getMethod())) {
                Map<String, String> parameters = new HashMap<>(16);
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String name = parameterNames.nextElement();
                    String value = request.getParameter(name);
                    parameters.put(name, value);
                }
                MDC.put("context.parameters", parameters.toString());

            } else if ("POST".equals(request.getMethod())) {
                newRequest = new HttpBodyReaderWrapper((HttpServletRequest) servletRequest);
                String body = (new HttpBodyReaderWrapper(request)).getBody();
                MDC.put("context.parameters", body);
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
}
