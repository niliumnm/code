package com.cdut.controller;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;


@Data
public class LoginController extends ActionSupport {
    private String username;
    private String password;
    private boolean admin;

    public String login()  {
        System.out.println("Login");
        if(username.equals(password)){
            return SUCCESS;
        }else {
            return ERROR;
        }
    }

    public String adminLogin(){
        System.out.println("执行Admin方法");
        //这个值和xml里的result对上，才跳转
        return "AdminSuccess";
    }

    public String normLogin() {
        System.out.println("执行User方法");
        return "NormSuccess";
    }


    public String register() {
        return SUCCESS;
    }
}
