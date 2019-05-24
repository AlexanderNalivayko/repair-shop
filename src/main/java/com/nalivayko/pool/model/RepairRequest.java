package com.nalivayko.pool.model;

import com.nalivayko.pool.model.enums.RepairStatus;

import java.util.Date;

public class RepairRequest {
    private Integer id;
    private User user;
    private Item item;
    private RepairStatus repairStatus;
    private Date creationDate;
    private String description;
    private Long cost;

    public RepairRequest(Integer id, User user, Item item, RepairStatus repairStatus, Date creationDate,
                         String description, Long cost) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.repairStatus = repairStatus;
        this.creationDate = creationDate;
        this.description = description;
        this.cost = cost;
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

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
