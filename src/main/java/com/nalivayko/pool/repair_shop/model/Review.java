package com.nalivayko.pool.repair_shop.model;

import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;

import java.math.BigDecimal;
import java.util.Date;

public class Review {
    private Integer id;
    private ReviewStatus status;
    private String time;
    private BigDecimal cost;
    private String rejectReason;

    public Review(Integer id, ReviewStatus status, String time, BigDecimal cost, String rejectReason) {
        this.id = id;
        this.status = status;
        this.time = time;
        this.cost = cost;
        this.rejectReason = rejectReason;
    }

    public Review(ReviewStatus status, BigDecimal cost) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
