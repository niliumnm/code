package com.learn.controller;

import com.learn.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.function.Consumer;


/*** This class draws a bar chart.
 * @author Zara Ali
 * @version 1.2
 */

@Controller
public class HelloController {
    @Autowired
    private Person person;

    //multipartFile自动封装过来的文件


    /**
     * This method inputs a number from the user.
     * @return The value input as a double.
     * @param name 传入姓名
     * @exception IOException On input error.
     * @see IOException
     */
    @ResponseBody
    @PostMapping("/name")
    public String soutName(@RequestParam("name") String name) throws IOException{
        Consumer<String> consumer = (String s) -> {
            System.out.println(s);
        };
        consumer.accept("测试");
        return "你好: " + name;
    }

}
