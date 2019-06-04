package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.Review;

public interface ReviewDAO {

    int create(Review review);

    boolean update(Review review);
}
