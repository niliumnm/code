package com.cdut.ssmp.utils;

import lombok.Data;

@Data

public class R {
    private Boolean message;
    private Object data;

    public R(){};
    public R(boolean flag) {
        this.message = flag;
    }


    public R(boolean flag,Object data) {
        this.message = flag;
        this.data = data;
    }

    public static R success(Object data) {
        R r = new R();
        r.setData(data);
        return r;
    }
}
