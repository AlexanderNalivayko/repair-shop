package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Feedback;
import com.nalivayko.pool.repair_shop.model.User;
import org.springframework.data.domain.Page;

public interface FeedbackService {

    Page<Feedback> getAll(int limit, int offset);

    void delete(int id);

    void create(User user, String text);

    long countAll();
}
