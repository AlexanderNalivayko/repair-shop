package com.nalivayko.pool.services;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.FeedbackDAO;

import java.util.List;

public class DefaultFeedbackService implements FeedbackService {
    private FeedbackDAO feedbackDAO;
    private TransactionManager transactionManager;

    public DefaultFeedbackService(TransactionManager transactionManager, FeedbackDAO feedbackDAO) {
        this.transactionManager = transactionManager;
        this.feedbackDAO = feedbackDAO;
    }

    @Override
    public List<Feedback> getAll() {
        transactionManager.getConnection();
        List<Feedback> feedbacks = feedbackDAO.findAll();
        transactionManager.closeConnection();
        return feedbacks;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void create(User user, String text) {
        transactionManager.getConnection();
        feedbackDAO.create(new Feedback(user, text));
        transactionManager.closeConnection();
    }
}
