package com.stelpolvo.wiki.exception;

public class UserException extends RuntimeException {
    private String msg;
    public UserException() {
        this.msg="登录失败";
    }

    public UserException(String message) {
        this.msg = message;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
