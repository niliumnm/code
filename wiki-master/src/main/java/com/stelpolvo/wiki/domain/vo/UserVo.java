package com.stelpolvo.wiki.domain.vo;

public class UserVo {
    private Long id;

    private String loginName;

    private String name;

    private String token;

    public UserVo(Long id, String loginName, String name, String token) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.token = token;
    }

    public UserVo() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
