package com.stelpolvo.wiki.domain;

import org.springframework.util.ObjectUtils;

public class User {
    private Long id;

    private String loginName;

    private String name;

    private String password;

    public boolean isValided() {
        if (password != null && password.length() > 0) {
            return password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
        }
        return false;
    }

    public boolean isValided(String password) {
        if (password != null && password.length() > 0) {
            return password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
        }
        return false;
    }

    public boolean isNotNull() {
        return !ObjectUtils.isEmpty(this.loginName) && !ObjectUtils.isEmpty(this.name) && !ObjectUtils.isEmpty(this.password);
    }

    public User(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", loginName=").append(loginName);
//        sb.append(", name=").append(name);
//        sb.append(", password=").append(password);
//        sb.append("]");
//        return sb.toString();
//    }
}