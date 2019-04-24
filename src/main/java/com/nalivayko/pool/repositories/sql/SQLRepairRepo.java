package com.nalivayko.pool.repositories.sql;

import com.nalivayko.pool.domain.Repair;
import com.nalivayko.pool.domain.RepairStatus;
import com.nalivayko.pool.exceptions.InternalAppException;
import com.nalivayko.pool.exceptions.UserRequestException;
import com.nalivayko.pool.repositories.ConnectionManager;
import com.nalivayko.pool.repositories.RepairRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLRepairRepo implements RepairRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlConnectionManager.class);

    private ConnectionManager connectionManager;

    public SQLRepairRepo(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void add(Repair repair) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairQuery.INSERT_REPAIR_REQUEST)) {
            preparedStatement.setObject(1, repair.getId());
            preparedStatement.setObject(2, repair.getUserId());
            preparedStatement.setObject(3, repair.getStatus().toString());
            preparedStatement.setObject(4, repair.getItem());
            preparedStatement.setObject(5, repair.getDescription());
            preparedStatement.setObject(6, repair.getPictureUrl());
            preparedStatement.setObject(7, repair.getPrice());
            preparedStatement.setObject(8, repair.getMasterNotes());
            preparedStatement.setObject(9, repair.getUserFeedback());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't add request", e);
            throw new UserRequestException(e);
        }
    }

    @Override
    public List<Repair> findByUserId(int userId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairQuery.SELECT_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Repair> repairs = new ArrayList<>();
                while (resultSet.next()) {
                    repairs.add(resultSetToRepairRequest(resultSet));
                }
                return repairs;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find RepairRequests", e);
            throw new InternalAppException(e);
        }
    }

    @Override
    public List<Repair> findAll() {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(RepairQuery.SELECT_ALL)) {
                List<Repair> repairs = new ArrayList<>();
                while (resultSet.next()) {
                    repairs.add(resultSetToRepairRequest(resultSet));
                }
                return repairs;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find repairRequests", e);
            throw new InternalAppException(e);
        }
    }


    @Override
    public void update(Repair repair) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairQuery.UPDATE_BY_ID)) {
            preparedStatement.setObject(1, repair.getUserId());
            preparedStatement.setObject(2, repair.getStatus().toString());
            preparedStatement.setObject(3, repair.getItem());
            preparedStatement.setObject(4, repair.getDescription());
            preparedStatement.setObject(5, repair.getPictureUrl());
            preparedStatement.setObject(6, repair.getPrice());
            preparedStatement.setObject(7, repair.getMasterNotes());
            preparedStatement.setObject(8, repair.getUserFeedback());
            preparedStatement.setObject(9, repair.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't add Repair", e);
            throw new InternalAppException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RepairQuery.DELETE_BY_ID)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't delete Repair", e);
            throw new InternalAppException(e);
        }
    }

    private Repair resultSetToRepairRequest(ResultSet resultSet) throws SQLException {
        return new Repair.Builder()
                .setId(resultSet.getLong(1))
                .setUserId(resultSet.getInt(2))
                .setStatus(RepairStatus.valueOf(resultSet.getString(3)))
                .setItem(resultSet.getString(4))
                .setDescription(resultSet.getString(5))
                .setPictureUrl(resultSet.getString(6))
                .setPrice(resultSet.getLong(7))
                .setMasterNotes(resultSet.getString(8))
                .setUserFeedback(resultSet.getString(9))
                .build();
    }
}
