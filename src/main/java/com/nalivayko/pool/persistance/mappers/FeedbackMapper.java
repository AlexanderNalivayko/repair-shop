package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.persistance.dao.sql.query.FeedbackQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements Mapper<Feedback> {

    @Override
    public Feedback getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Feedback(resultSet.getInt(FeedbackQuery.ID),
                new UserMapper().getEntity(resultSet),
                resultSet.getString(FeedbackQuery.TEXT));
    }
}
