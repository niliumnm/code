package com.cdut.stutstest;


import com.cdut.pojo.student;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Hello extends ActionSupport implements ModelDriven<student> {
    private student student;

    @Override
    public student getModel(){
        if (student == null) {
            student = new student();
        }
        return student;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("默认调用的方法！");
        System.out.println(student);
        return SUCCESS;
    }

}