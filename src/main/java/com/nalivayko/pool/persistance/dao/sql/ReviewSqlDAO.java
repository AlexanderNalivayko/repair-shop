package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.Review;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.ReviewDAO;
import com.nalivayko.pool.persistance.dao.sql.query.ReviewQuery;

/**
 * @see com.nalivayko.pool.persistance.dao.sql.AbstractSqlDAO
 */
public class ReviewSqlDAO extends AbstractSqlDAO<Review> implements ReviewDAO {

    public ReviewSqlDAO(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public int create(Review review) {
        return create(ReviewQuery.INSERT, preparedStatement -> {
            preparedStatement.setString(1, review.getStatus().toString());
            preparedStatement.setObject(2, review.getCost());
            preparedStatement.setObject(3, review.getRejectReason());
        });
    }
}
