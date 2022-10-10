package com.cdut.stutstest;

import com.opensymphony.xwork2.ActionSupport;

import javax.crypto.interfaces.PBEKey;

//这是一个controller
//控制器中要有getter和setter
public class check extends ActionSupport {

    //变量从哪来？从jsp同名的表单元素中来
    public String name;
    public int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String execute(){
        return "success";
    }

    public String adminLogin(){
        System.out.println("执行Admin方法");
        return "AdminSuccess";


    }

    public String normLogin() {
        System.out.println("执行User方法");
        return "NormSuccess";
    }
}
