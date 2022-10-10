package com.stelpolvo.wiki.utils;

import com.stelpolvo.wiki.domain.vo.UserVo;

public class UserContext {
    private static ThreadLocal<UserVo> user = new ThreadLocal<>();

    public static UserVo getUser() {
        return user.get();
    }

    public static void setUser(UserVo user) {
        UserContext.user.set(user);
    }

}
