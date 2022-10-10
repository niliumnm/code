package com.stelpolvo.wiki.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.utils.RequestContext;
import com.stelpolvo.wiki.utils.SnowFlake;
import org.apache.ibatis.binding.MapperMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Component
@Aspect
public class LogAspect {

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.stelpolvo.wiki.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        Object result = null;
        currentTime.set(System.currentTimeMillis());
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log annotation = methodSignature.getMethod().getAnnotation(Log.class);
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        RequestContext.setRemoteAddr(getRemoteIp(request));
        // 打印请求信息
        LOG.info("------------- 请求开始 -------------");


        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        /*
         * 排除字段，敏感字段或太长的字段不显示
         * 排除：returnType:write javaBean error, fastjson version 1.2.76、file
         * 敏感字段: password
         */
        String[] excludeProperties = {"password", "file", "returnType"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            LOG.info("------------- 发生异常 --------------------");
            generateLog(annotation, request, signature, arguments, excludefilter, result, e);
            throw e;
        }
        generateLog(annotation, request, signature, arguments, excludefilter, result, null);
        currentTime.remove();
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    }

    /**
     * 使用nginx做反向代理，需要用该方法才能取到真实的远程IP
     * proxy_set_header Host $http_host;
     * proxy_set_header X-Real-IP $remote_addr;
     * proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
     * proxy_set_header X-Forwarded-Proto $scheme;
     *
     * @param request
     * @return
     */
    public String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public void generateLog(Log annotation, HttpServletRequest request, Signature signature, Object[] arguments, PropertyPreFilters.MySimplePropertyPreFilter excludefilter, Object result, Throwable e) {
        if (e != null) {
            LOG.info("未知异常: {}", e.getMessage());
        }
        LOG.info("用户操作: {}", annotation.value());
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), signature.getName());
        LOG.info("远程地址: {}", RequestContext.getRemoteAddr());
        LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludefilter));
        LOG.info("返回结果: {}", JSONObject.toJSONString(result, excludefilter));
        LOG.info("------------- 请求结束 耗时：{} ms -------------", System.currentTimeMillis() - currentTime.get());
    }
}