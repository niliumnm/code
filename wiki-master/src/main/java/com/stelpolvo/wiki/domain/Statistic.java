package com.stelpolvo.wiki.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Statistic {
    @JsonFormat(pattern = "MM-dd",timezone = "GMT+8")
    private Date date;

    private int viewCount;

    private int voteCount;

    private int viewIncrease;

    private int voteIncrease;

    public Statistic(Date date, int viewCount, int voteCount, int viewIncrease, int voteIncrease) {
        this.date = date;
        this.viewCount = viewCount;
        this.voteCount = voteCount;
        this.viewIncrease = viewIncrease;
        this.voteIncrease = voteIncrease;
    }

    public Statistic() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getViewIncrease() {
        return viewIncrease;
    }

    public void setViewIncrease(int viewIncrease) {
        this.viewIncrease = viewIncrease;
    }

    public int getVoteIncrease() {
        return voteIncrease;
    }

    public void setVoteIncrease(int voteIncrease) {
        this.voteIncrease = voteIncrease;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "date=" + date +
                ", viewCount=" + viewCount +
                ", voteCount=" + voteCount +
                ", viewIncrease=" + viewIncrease +
                ", voteIncrease=" + voteIncrease +
                '}';
    }
}
