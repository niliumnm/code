package com.learn.config;

import ch.qos.logback.core.db.DBHelper;
import com.learn.bean.Car;
import com.learn.bean.Pet;
import com.learn.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;


@EnableConfigurationProperties(Car.class)
@Configuration(proxyBeanMethods = true)//告诉Springboot这是一个配置类
@Import(User.class)


public class myConfig {



//    @Bean
    @ConditionalOnBean(name="TomCat")
    public User testUser() {
        User user = new User("测试用户", 18);
        return user;
    }
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent((false));
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }

}
