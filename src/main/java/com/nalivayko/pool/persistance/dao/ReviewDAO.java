package com.nalivayko.pool.persistance.dao;


import com.nalivayko.pool.model.Review;

public interface ReviewDAO {

    public int create(Review review);

    public boolean update(Review review);
}
