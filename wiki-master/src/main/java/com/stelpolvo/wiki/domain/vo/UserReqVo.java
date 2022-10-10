package com.stelpolvo.wiki.domain.vo;

public class UserReqVo extends BaseVo{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserReqVo{" +
                "username='" + username + '\'' +
                '}';
    }
}
