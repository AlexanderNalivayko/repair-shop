package com.nalivayko.pool.model;

public class Contacts {

    private int id;
    private String email;
    private String phone;

    public Contacts(int id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
