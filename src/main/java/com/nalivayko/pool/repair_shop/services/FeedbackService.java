package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Feedback;
import com.nalivayko.pool.repair_shop.model.User;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getAll(int limit, int offset);

    void delete(int id);

    void create(User user, String text);

    /**
     * @return count of all records in storage
     */
    int countAll();
}
