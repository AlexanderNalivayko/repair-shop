package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.Feedback;
import com.nalivayko.pool.persistance.dao.sql.query.FeedbackQuery;
import com.nalivayko.pool.util.FormattingUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements Mapper<Feedback> {

    @Override
    public Feedback getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Feedback(resultSet.getInt(FeedbackQuery.ID),
                new SecureUserMapper().getEntity(resultSet),
                resultSet.getString(FeedbackQuery.TEXT),
                FormattingUtil.dateToString(resultSet.getTimestamp(FeedbackQuery.CREATION_TIME)));
    }
}
