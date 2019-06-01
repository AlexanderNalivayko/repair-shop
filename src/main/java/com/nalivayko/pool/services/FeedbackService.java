package com.nalivayko.pool.services;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.model.User;

import java.util.List;

public interface FeedbackService {

    public List<Feedback> getAll(int limit, int offset);

    public void delete(int id);

    public void create(User user, String text);

    int getRecordsCount();
}
