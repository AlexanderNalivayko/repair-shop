package com.nalivayko.pool.model;

public class Item {
    private Integer id;
    private String productType;
    private String brand;
    private String name;

    public Item(Integer id, String productType, String brand, String name) {
        this.id = id;
        this.productType = productType;
        this.brand = brand;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
