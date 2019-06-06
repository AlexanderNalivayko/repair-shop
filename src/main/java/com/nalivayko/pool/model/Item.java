package com.nalivayko.pool.model;

public class Item {
    private Integer id;
    private String itemType;
    private String brand;
    private String name;

    public Item(Integer id, String itemType, String brand, String name) {
        this.id = id;
        this.itemType = itemType;
        this.brand = brand;
        this.name = name;
    }

    public Item(String itemType, String brand, String name) {
        this.itemType = itemType;
        this.brand = brand;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
