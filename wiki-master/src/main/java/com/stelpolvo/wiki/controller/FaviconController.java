package com.stelpolvo.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {
    @GetMapping("favicon.ico")
    public void doNothing(){
        // 从Spring Boot 2.2开始，spring.mvc.favicon.enabled=false 被弃用
    }
}
