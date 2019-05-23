package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairStatus;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.RepairRequestDAO;
import com.nalivayko.pool.persistance.dao.sql.query.RepairQuery;
import com.nalivayko.pool.persistance.mappers.RepairRequestMapper;

import java.util.List;


public class RepairRequestSqlDAO extends AbstractSqlDAO<RepairRequest> implements RepairRequestDAO {


    public RepairRequestSqlDAO(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public int create(RepairRequest repairRequest) {
        return create(RepairQuery.INSERT, preparedStatement -> {
            preparedStatement.setString(1, repairRequest.getRepairStatus().toString());
            preparedStatement.setInt(2, repairRequest.getUser().getId());
            preparedStatement.setInt(3, repairRequest.getId());
            preparedStatement.setString(4, repairRequest.getDescription());
            preparedStatement.setLong(5, repairRequest.getCost());
        });
    }

    @Override
    public List<RepairRequest> findByUserId(int userId) {
        return findAll(RepairQuery.SELECT_BY_USER_ID, preparedStatement ->
                preparedStatement.setInt(1, userId), new RepairRequestMapper());
    }

    @Override
    public List<RepairRequest> findByStatus(RepairStatus status) {
        return findAll(RepairQuery.SELECT_BY_STATUS, preparedStatement ->
                preparedStatement.setString(1, status.toString()), new RepairRequestMapper());
    }

    @Override
    public List<RepairRequest> findAll() {
        return findAll(RepairQuery.SELECT_ALL, new RepairRequestMapper());
    }

    @Override
    public boolean update(RepairRequest repairRequest) {
        return updateDelete(RepairQuery.UPDATE_BY_ID, preparedStatement -> {
            preparedStatement.setString(1, repairRequest.getRepairStatus().toString());
            preparedStatement.setInt(2, repairRequest.getUser().getId());
            preparedStatement.setInt(3, repairRequest.getItem().getId());
            preparedStatement.setString(4, repairRequest.getDescription());
            preparedStatement.setLong(5, repairRequest.getCost());
        });
    }

    @Override
    public boolean delete(int id) {
        return updateDelete(RepairQuery.DELETE_BY_ID, preparedStatement ->
                preparedStatement.setInt(1, id));
    }
}
