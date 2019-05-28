package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.Review;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.ReviewDAO;
import com.nalivayko.pool.persistance.dao.sql.query.ReviewQuery;

public class ReviewSqlDAO extends AbstractSqlDAO<Review> implements ReviewDAO {

    public ReviewSqlDAO(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public int create(Review review) {
        return create(ReviewQuery.INSERT, preparedStatement -> {
            preparedStatement.setString(1, review.getStatus().toString());
            preparedStatement.setLong(2, review.getCost());
        });
    }

    @Override
    public boolean update(Review review) {
        return updateDelete(ReviewQuery.UPDATE_BY_ID, preparedStatement -> {
            preparedStatement.setString(1, review.getStatus().toString());
            preparedStatement.setLong(2, review.getCost());
        });
    }
}
