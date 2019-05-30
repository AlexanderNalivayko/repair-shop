package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.Review;
import com.nalivayko.pool.model.enums.ReviewStatus;
import com.nalivayko.pool.persistance.dao.sql.query.ReviewQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements Mapper<Review> {

    @Override
    public Review getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Review(resultSet.getInt(ReviewQuery.ID),
                ReviewStatus.valueOf(resultSet.getString(ReviewQuery.REVIEW_STATUS)),
                resultSet.getDate(ReviewQuery.REVIEW_TIME),
                resultSet.getLong(ReviewQuery.COST));
    }
}
