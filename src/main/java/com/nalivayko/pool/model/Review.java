package com.nalivayko.pool.model;

import com.nalivayko.pool.model.enums.ReviewStatus;

import java.util.Date;

public class Review {
    private Integer id;
    private ReviewStatus status;
    private Date time;
    private Long cost;

    public Review(Integer id, ReviewStatus status, Date time, Long cost) {
        this.id = id;
        this.status = status;
        this.time = time;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
