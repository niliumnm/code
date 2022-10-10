package com.stelpolvo.wiki.interceptor;

import com.alibaba.fastjson.JSON;
import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.domain.vo.UserVo;
import com.stelpolvo.wiki.exception.UserException;
import com.stelpolvo.wiki.utils.UserContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @Log("用户认证")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // OPTIONS请求不做校验
        if (request.getMethod().toUpperCase(Locale.ROOT).equals("OPTIONS")) {
            return true;
        }
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new UserException("请先登录");
        }
        String o = redisTemplate.opsForValue().get(token);
        if (o == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new UserException("请先登录");
        } else {
            UserContext.setUser(JSON.parseObject(o, UserVo.class));
            return true;
        }
    }
}
