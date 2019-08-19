package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Feedback;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedFeedBackCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DefaultFeedbackService implements FeedbackService {

    @Autowired
    private CustomizedFeedBackCrudRepository feedBackRepo;

    @Override
    public Page<Feedback> getAll(Pageable pageable) {
        return feedBackRepo.findAll(pageable);
    }

    @Override
    public void delete(int id) {
        feedBackRepo.deleteById(id);
    }

    @Override
    public void create(User user, String text) {
        feedBackRepo.save(Feedback.builder()
                .user(user)
                .text(text)
                .build());
    }

    @Override
    public long countAll() {
        return feedBackRepo.count();
    }
}
