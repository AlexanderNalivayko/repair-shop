package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
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
            preparedStatement.setInt(3, repairRequest.getReview().getId());
            preparedStatement.setString(4, repairRequest.getRepairRequestStatus().toString());
            preparedStatement.setString(5, repairRequest.getDescription());
        });
    }

    @Override
    public List<RepairRequest> findByUserId(int userId) {
        return findAll(RepairRequestQuery.SELECT_BY_USER_ID, preparedStatement ->
                preparedStatement.setInt(1, userId), new RepairRequestMapper());
    }

    @Override
    public List<RepairRequest> findByStatus(RepairRequestStatus status) {
        return findAll(RepairRequestQuery.SELECT_BY_STATUS, preparedStatement ->
                preparedStatement.setString(1, status.toString()), new RepairRequestMapper());
    }

    @Override
    public List<RepairRequest> findAll() {
        return findAll(RepairRequestQuery.SELECT_ALL, new RepairRequestMapper());
    }

    @Override
    public boolean update(RepairRequest repairRequest) {
        return updateDelete(RepairRequestQuery.UPDATE_BY_ID, preparedStatement -> {
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
        return updateDelete(RepairRequestQuery.DELETE_BY_ID, preparedStatement ->
                preparedStatement.setInt(1, id));
    }
}
