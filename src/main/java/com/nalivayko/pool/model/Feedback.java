package com.nalivayko.pool.model;

import java.util.Date;

public class Feedback {
    private int id;
    private User user;
    private String text;
    private Date creationTime;

    public Feedback(int id, User user, String text, Date creationTime) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.creationTime = creationTime;
    }

    public Feedback(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}

