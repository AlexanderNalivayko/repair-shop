package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.RepairRequestDAO;
import com.nalivayko.pool.persistance.dao.sql.query.RepairRequestQuery;
import com.nalivayko.pool.persistance.mappers.RepairRequestMapper;

import java.util.List;

public class RepairRequestSqlDAO extends AbstractSqlDAO<RepairRequest> implements RepairRequestDAO {

    public RepairRequestSqlDAO(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public int create(RepairRequest repairRequest) {
        return create(RepairRequestQuery.INSERT, preparedStatement -> {
            preparedStatement.setInt(1, repairRequest.getUser().getId());
            preparedStatement.setInt(2, repairRequest.getItem().getId());
            preparedStatement.setString(3, repairRequest.getRepairRequestStatus().toString());
            preparedStatement.setString(4, repairRequest.getDescription());
        });
    }

    @Override
    public List<RepairRequest> findByUserId(int userId, int limit, int offset) {
        return findAll(RepairRequestQuery.SELECT_BY_USER_ID_WITH_LIMIT, preparedStatement -> {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
        }, new RepairRequestMapper());
    }

    @Override
    public List<RepairRequest> findByRepairRequestStatus(RepairRequestStatus status) {
        return findAll(RepairRequestQuery.SELECT_BY_STATUS, preparedStatement ->
                preparedStatement.setString(1, status.toString()), new RepairRequestMapper());
    }

    @Override
    public List<RepairRequest> findByReviewAndRequestStatus(ReviewStatus reviewStatus,
                                                            RepairRequestStatus repairRequestStatus) {
        return findAll(RepairRequestQuery.SELECT_BY_REVIEW_AND_REQUEST_STATUS, preparedStatement -> {
            preparedStatement.setString(1, reviewStatus.toString());
            preparedStatement.setString(2, repairRequestStatus.toString());
        }, new RepairRequestMapper());
    }

    @Override
    public List<RepairRequest> findAll() {
        return findAll(RepairRequestQuery.SELECT_ALL, new RepairRequestMapper());
    }

    @Override
    public int countForUser(int userId) {
        return count(RepairRequestQuery.COUNT_FOR_USER, preparedStatement ->
                preparedStatement.setInt(1, userId));
    }

    @Override
    public boolean updateReviewId(int RepairRequestId, int ReviewId) {
        return updateOrDelete(RepairRequestQuery.UPDATE_REVIEW, preparedStatement -> {
            preparedStatement.setInt(1, ReviewId);
            preparedStatement.setInt(2, RepairRequestId);
        });
    }

    @Override
    public boolean updateStatus(int RepairRequestId, RepairRequestStatus repairRequestStatus) {
        return updateOrDelete(RepairRequestQuery.UPDATE_STATUS, preparedStatement -> {
            preparedStatement.setString(1, repairRequestStatus.toString());
            preparedStatement.setInt(2, RepairRequestId);
        });
    }

    @Override
    public boolean update(RepairRequest repairRequest) {
        return updateOrDelete(RepairRequestQuery.UPDATE_BY_ID, preparedStatement -> {
            preparedStatement.setInt(1, repairRequest.getUser().getId());
            preparedStatement.setInt(2, repairRequest.getItem().getId());
            preparedStatement.setInt(3, repairRequest.getReview().getId());
            preparedStatement.setString(4, repairRequest.getRepairRequestStatus().toString());
            preparedStatement.setString(5, repairRequest.getDescription());
            preparedStatement.setInt(6, repairRequest.getId());
        });
    }

    @Override
    public boolean delete(int id) {
        return updateOrDelete(RepairRequestQuery.DELETE_BY_ID, preparedStatement ->
                preparedStatement.setInt(1, id));
    }
}
