package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class RequestController {
    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了");
        request.setAttribute("code", 200);
        return "forward:/success";

    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request) {
        Object msg1 = request.getAttribute("msg");
        Map<String, Object> map = new HashMap<>();
        map.put("reqMethod_msg", msg1);
        map.put("annotation_msg", msg);
        return map;
    }

    @ResponseBody
    @GetMapping("/carsell/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                           @MatrixVariable("brand") List<String> brand) {
        Map<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        return map;
    }
}
