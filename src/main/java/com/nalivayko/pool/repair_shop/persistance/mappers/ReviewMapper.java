package com.nalivayko.pool.repair_shop.persistance.mappers;

import com.nalivayko.pool.repair_shop.model.Review;
import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;
import com.nalivayko.pool.repair_shop.persistance.dao.sql.query.ReviewQuery;
import com.nalivayko.pool.repair_shop.util.FormattingUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements Mapper<Review> {

    @Override
    public Review getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Review(resultSet.getInt(ReviewQuery.ID),
                resultSet.getString(ReviewQuery.REVIEW_STATUS) == null ?
                        null : ReviewStatus.valueOf(resultSet.getString(ReviewQuery.REVIEW_STATUS)),
                FormattingUtil.dateToString(resultSet.getTimestamp(ReviewQuery.REVIEW_TIME)),
                resultSet.getBigDecimal(ReviewQuery.COST),
                resultSet.getString(ReviewQuery.REJECT_REASON));
    }
}
