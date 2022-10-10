package com.cdut.stutstest;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
    //方法返回值都是Action
    private String username;
    private String password;

    public String login() {
        System.out.println("login");
        return SUCCESS;
    }

    public String regist() {
        System.out.println("regist");
        return SUCCESS;
    }

    public TestAction() {
    }

    public TestAction(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
