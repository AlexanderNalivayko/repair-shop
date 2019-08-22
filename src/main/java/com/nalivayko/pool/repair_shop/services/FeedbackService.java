package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    Page<Feedback> getAll(Pageable pageable);

    void delete(int id);

    void create(String username, String text);

    long countAll();
}
