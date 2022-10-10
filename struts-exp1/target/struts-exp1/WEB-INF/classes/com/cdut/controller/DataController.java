package com.cdut.controller;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Data;

import java.util.Date;

@Data
public class DataController extends ActionSupport  {

//    private Student student;
    private Date birthday;

    @Override
    public String execute() throws Exception {
        System.out.println("Data");
        System.out.println(birthday);
        return SUCCESS;
    }

//    @Override
//    public Student getModel() {
//        return new Student();
//    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
