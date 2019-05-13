package com.nalivayko.pool.services;

import com.nalivayko.pool.model.Feedback;

import java.util.List;

public interface FeedbackService {

    public List<Feedback> getAll();

    public void delete(int id);

    public void create(int accountId, String text);
}
