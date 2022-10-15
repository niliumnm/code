package com.ruiji.fliter;

import com.alibaba.fastjson.JSON;
import com.ruiji.utils.BaseContext;
import com.ruiji.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
@Slf4j
public class LoginFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    public static final String[] pass = new String[]{
            "/backend/**",
            "/front/**",
            "/employee/login",
            "/employee/logout",
            "/common/**"
    };



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        if (match(pass, requestURI)) {
            //不需要拦截 直接放行
            log.info("本次请求{}不需要拦截",requestURI);
            filterChain.doFilter(request, response);
        } else if (request.getSession().getAttribute("employee") != null) {
            log.info("LoginFilter的Id"+Thread.currentThread().getId());

            Long empId = (Long) request.getSession().getAttribute("employee");

            //已登录 放行
            log.info("已登录账户" + empId);

            //将id存入
            BaseContext.setCurrentId(empId);


            filterChain.doFilter(request, response);
        } else {
            log.info("用户未登录");
            response.getWriter().write(JSON.toJSONString(R.error("请先登录")));
        }
    }

    private Boolean match(String[] urls, String URI) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, URI)) {
                return true;
            }
        }
        return false;
    }


}
