package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Feedback;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.persistance.TransactionManager;
import com.nalivayko.pool.repair_shop.persistance.dao.FeedbackDAO;

import java.util.List;

/**
 * Provides methods to work with Feedback (create, find, delete ...)
 */
public class DefaultFeedbackService implements FeedbackService {
    private FeedbackDAO feedbackDAO;
    private TransactionManager transactionManager;

    public DefaultFeedbackService(TransactionManager transactionManager, FeedbackDAO feedbackDAO) {
        this.transactionManager = transactionManager;
        this.feedbackDAO = feedbackDAO;
    }

    @Override
    public List<Feedback> getAll(int limit, int offset) {
        try {
            transactionManager.getConnection();
            return feedbackDAO.findAll(limit, offset);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public void delete(int id) {
        try {
            transactionManager.getConnection();
            feedbackDAO.delete(id);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public void create(User user, String text) {
        try {
            transactionManager.getConnection();
            feedbackDAO.create(new Feedback(user, text));
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public int countAll() {
        try {
            transactionManager.getConnection();
            return feedbackDAO.count();
        } finally {
            transactionManager.closeConnection();
        }
    }
}
