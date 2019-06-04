package com.nalivayko.pool.services;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.model.User;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getAll(int limit, int offset);

    void delete(int id);

    void create(User user, String text);

    int getRecordsCount();
}
