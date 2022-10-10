package com.cdut.controller;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;

@Data
public class ValidateController extends ActionSupport {
    private String username;
    private String password;


    public String login() {
        System.out.println("Login");
        if(username.equals(password)){
            return SUCCESS;
        }else {
            return ERROR;
        }
    }
    @Override
    public void validate() {
        if (getUsername() == null) {
            addFieldError("username", "用户名为空,请输入用户名");
        }
        if (getUsername().length() < 6) {
            addFieldError("password", "密码小于6位");
        }
        if (!getUsername().equals(getPassword())) {
            addFieldError("password", "用户名和密码不匹配");
        }
    }
}
