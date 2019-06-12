package com.nalivayko.pool.repair_shop.model;

import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;

public class RepairRequest {
    private Integer id;
    private User user;
    private Item item;
    private Review review;
    private RepairRequestStatus repairRequestStatus;
    private String creationTime;
    private String description;

    public RepairRequest(Integer id, User user, Item item, Review review, RepairRequestStatus repairRequestStatus,
                         String creationTime, String description) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.review = review;
        this.repairRequestStatus = repairRequestStatus;
        this.creationTime = creationTime;
        this.description = description;
    }

    public RepairRequest(User user, Item item, RepairRequestStatus repairRequestStatus, String description) {
        this.user = user;
        this.item = item;
        this.repairRequestStatus = repairRequestStatus;
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
