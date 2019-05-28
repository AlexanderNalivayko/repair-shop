package com.nalivayko.pool.model;

import com.nalivayko.pool.model.enums.RepairRequestStatus;

import java.util.Date;

public class RepairRequest {
    private Integer id;
    private User user;
    private Item item;
    private Review review;
    private RepairRequestStatus repairRequestStatus;
    private Date creationTime;
    private String description;

    public RepairRequest(Integer id, User user, Item item, Review review, RepairRequestStatus repairRequestStatus,
                         Date creationTime, String description) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.review = review;
        this.repairRequestStatus = repairRequestStatus;
        this.creationTime = creationTime;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public RepairRequestStatus getRepairRequestStatus() {
        return repairRequestStatus;
    }

    public void setRepairRequestStatus(RepairRequestStatus repairRequestStatus) {
        this.repairRequestStatus = repairRequestStatus;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
