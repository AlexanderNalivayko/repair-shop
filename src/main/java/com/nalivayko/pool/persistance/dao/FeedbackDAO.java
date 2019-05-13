package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.Feedback;

import java.util.List;

public interface FeedbackDAO {

    public int create(Feedback feedback);

    public List<Feedback> findAll();

    public boolean delete(int id);
}
