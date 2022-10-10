package com.cdut.pojo;

public class student {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public student() {
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
