package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Feedback;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedFeedBackCrudRepository;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedUserCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@NoArgsConstructor
public class DefaultFeedbackService implements FeedbackService {

    @Autowired
    private CustomizedFeedBackCrudRepository feedBackRepo;
    @Autowired
    private CustomizedUserCrudRepository userRepo;

    @Override
    public Page<Feedback> getAll(Pageable pageable) {
        return feedBackRepo.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by("id").descending()));
    }

    @Override
    public void delete(int id) {
        feedBackRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void create(String username, String text) {
        if (text.length() > 255) {
            text = text.substring(0, 255);
        }
        feedBackRepo.save(Feedback.builder()
                .user(userRepo.findByUsername(username))
                .text(text)
                .creationTime(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build());
    }

    @Override
    public long countAll() {
        return feedBackRepo.count();
    }
}
