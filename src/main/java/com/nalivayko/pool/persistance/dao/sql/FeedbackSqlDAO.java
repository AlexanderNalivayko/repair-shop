package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.persistance.dao.FeedbackDAO;
import com.nalivayko.pool.persistance.dao.sql.query.FeedbackQuery;
import com.nalivayko.pool.persistance.dbcp.ConnectionManager;
import com.nalivayko.pool.persistance.mappers.FeedbackMapper;

import java.util.List;

public class FeedbackSqlDAO extends AbstractSqlDAO<Feedback> implements FeedbackDAO {

    public FeedbackSqlDAO(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public int create(Feedback feedback) {
        return create(FeedbackQuery.INSERT, preparedStatement -> {
            preparedStatement.setInt(1, feedback.getUser().getId());
            preparedStatement.setString(2, feedback.getText());
        });
    }

    @Override
    public List<Feedback> findAll() {
        return findAll(FeedbackQuery.SELECT_ALL, new FeedbackMapper());
    }

    @Override
    public boolean delete(int id) {
        return updateDelete(FeedbackQuery.DELETE_BY_ID, preparedStatement ->
                preparedStatement.setInt(1, id));
    }
}
