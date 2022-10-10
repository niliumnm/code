package com.stelpolvo.wiki.domain;

import java.util.List;

public class RespPage<T> {
    private long total;
    private List<T> list;

    public RespPage(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public RespPage() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RespPage{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
