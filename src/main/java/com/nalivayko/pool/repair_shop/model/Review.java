package com.nalivayko.pool.repair_shop.model;

import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;

import java.util.Date;

public class Review {
    private Integer id;
    private ReviewStatus status;
    private Date time;
    private Integer cost;
    private String rejectReason;

    public Review(Integer id, ReviewStatus status, Date time, Integer cost) {
        this.id = id;
        this.status = status;
        this.time = time;
        this.cost = cost;
    }

    public Review(ReviewStatus status, Integer cost) {
        this.status = status;
        this.cost = cost;
    }

    public Review(ReviewStatus status, String rejectReason) {
        this.status = status;
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
