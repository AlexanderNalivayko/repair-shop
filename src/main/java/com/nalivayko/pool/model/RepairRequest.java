package com.nalivayko.pool.model;

import com.nalivayko.pool.model.enums.RepairStatus;

public class RepairRequest {

    private Integer id;
    private RepairStatus repairStatus;
    private User user;
    private Item item;
    private String description;
    private Long cost;

    public RepairRequest(Integer id, RepairStatus repairStatus, User user, Item item, String description, Long cost) {
        this.id = id;
        this.repairStatus = repairStatus;
        this.user = user;
        this.item = item;
        this.description = description;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
