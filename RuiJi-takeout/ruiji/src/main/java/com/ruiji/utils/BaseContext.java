package com.ruiji.utils;

/**
 * 基于ThreadLocal，用于保存和获取用户id的工具类
 * @author shenyi
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }

}
