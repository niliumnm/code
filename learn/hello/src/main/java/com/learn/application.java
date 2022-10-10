package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;


@ServletComponentScan(basePackages = "com.learn.servlet")
@SpringBootApplication
public class application {

    public static void main(String[] args) {
        ConfigurableApplicationContext aop = SpringApplication.run(application.class, args);

    }

}
