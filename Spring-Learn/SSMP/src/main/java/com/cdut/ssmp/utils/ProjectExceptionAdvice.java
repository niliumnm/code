package com.cdut.ssmp.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public R doException(Exception ex){
        ex.printStackTrace();
        return new R();
    }
}
