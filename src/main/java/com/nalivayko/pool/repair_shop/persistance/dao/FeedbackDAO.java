package com.nalivayko.pool.repair_shop.persistance.dao;

import com.nalivayko.pool.repair_shop.model.Feedback;

import java.util.List;

public interface FeedbackDAO {

    int create(Feedback feedback);

    List<Feedback> findAll(int limit, int offset);

    boolean delete(int id);

    int count();
}
