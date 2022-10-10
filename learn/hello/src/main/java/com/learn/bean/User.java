package com.learn.bean;

import org.springframework.context.annotation.Bean;

public class User {
    private String name;
    private Integer age;
    private Pet pet;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pet=" + pet +
                '}';
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String s, int i) {
        this.name = s;
        this.age = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
