package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.Feedback;

import java.util.List;

public interface FeedbackDAO {

    public int create(Feedback feedback);

    public List<Feedback> findAll(int limit, int offset);

    public boolean delete(int id);

    public int count();
}
