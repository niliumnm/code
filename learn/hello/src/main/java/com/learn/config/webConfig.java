package com.learn.config;

import com.learn.interceptor.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//所有定制web功能的，都要实现WebMvcConfigurer
public class webConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/,", "/login","/static/**");

    }
}
