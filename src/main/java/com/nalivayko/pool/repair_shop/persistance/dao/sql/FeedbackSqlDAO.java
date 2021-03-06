package com.nalivayko.pool.repair_shop.persistance.dao.sql;

import com.nalivayko.pool.repair_shop.model.Feedback;
import com.nalivayko.pool.repair_shop.persistance.TransactionManager;
import com.nalivayko.pool.repair_shop.persistance.dao.FeedbackDAO;
import com.nalivayko.pool.repair_shop.persistance.dao.sql.query.FeedbackQuery;
import com.nalivayko.pool.repair_shop.persistance.mappers.FeedbackMapper;

import java.util.List;

/**
 * @see com.nalivayko.pool.repair_shop.persistance.dao.sql.AbstractSqlDAO
 */
public class FeedbackSqlDAO extends AbstractSqlDAO<Feedback> implements FeedbackDAO {

    public FeedbackSqlDAO(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public int create(Feedback feedback) {
        return create(FeedbackQuery.INSERT, preparedStatement -> {
            preparedStatement.setInt(1, feedback.getUser().getId());
            preparedStatement.setString(2, feedback.getText());
        });
    }

    @Override
    public List<Feedback> findAll(int limit, int offset) {
        return findAll(FeedbackQuery.SELECT_ALL_WITH_LIMIT, preparedStatement -> {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
        }, new FeedbackMapper());
    }

    @Override
    public boolean delete(int id) {
        return updateOrDelete(FeedbackQuery.DELETE_BY_ID, preparedStatement ->
                preparedStatement.setInt(1, id));
    }

    @Override
    public int count() {
        return count(FeedbackQuery.COUNT);
    }
}
