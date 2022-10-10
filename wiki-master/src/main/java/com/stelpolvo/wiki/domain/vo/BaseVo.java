package com.stelpolvo.wiki.domain.vo;

public class BaseVo {
    private int page;
    private int size;

    public BaseVo() {
        this.page = 1;
        this.size = 7;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDefault() {
        this.page = 1;
        this.size = 7;
    }

    @Override
    public String toString() {
        return "BaseVo{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}
