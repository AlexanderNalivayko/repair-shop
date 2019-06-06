package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.Feedback;

import java.util.List;

public interface FeedbackDAO {

    int create(Feedback feedback);

    List<Feedback> findAll(int limit, int offset);

    boolean delete(int id);

    int count();
}
