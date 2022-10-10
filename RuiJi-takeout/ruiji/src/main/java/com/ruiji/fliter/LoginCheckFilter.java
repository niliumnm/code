package com.ruiji.fliter;

import com.alibaba.fastjson.JSON;
import com.ruiji.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
        };
        String requestURI = request.getRequestURI();


        boolean check = match(requestURI, urls);
        if(check){
            log.info("本次请求{}不需要处理，直接放行",requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getSession().getAttribute("employee")!=null) {
            log.info("本次请求{}用户已登录，直接放行",requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        log.info("用户未登录");

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    private Boolean match(String URI, String[] urls) {
        for (String url : urls) {
            if (pathMatcher.match(url, URI)) {
                return true;
            }
        }
        return false;
    }
}
