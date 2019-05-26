package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.exceptions.InternalAppException;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.mappers.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSqlDAO<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSqlDAO.class);
    private TransactionManager transactionManager;

    public AbstractSqlDAO(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * @return auto-generated key(id) for created row
     */
    protected int create(String sqlQuery, PreparedStatementConsumer preparedStatementConsumer) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementConsumer.prepare(preparedStatement);
            int result = preparedStatement.executeUpdate();
            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            if (result > 0 && tableKeys.next()) {
                return tableKeys.getInt(1);
            } else {
                InternalAppException internalAppException = new InternalAppException("Can't create data in db");
                LOGGER.error(internalAppException.getMessage());
                throw internalAppException;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e.getMessage());
        }
    }

    protected T find(String sql, PreparedStatementConsumer preparedStatementConsumer, Mapper<T> mapper) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementConsumer.prepare(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.getEntity(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e);
        }
    }

    protected List<T> findAll(String query, Mapper<T> mapper) {
        Connection connection = transactionManager.getConnection();
        List<T> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(mapper.getEntity(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e);
        }
    }

    protected List<T> findAll(String sql, PreparedStatementConsumer preparedStatementConsumer, Mapper<T> mapper) {
        Connection connection = transactionManager.getConnection();
        List<T> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementConsumer.prepare(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    list.add(mapper.getEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e);
        }
        return list;
    }

    protected boolean updateDelete(String sql, PreparedStatementConsumer preparedStatementConsumer) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementConsumer.prepare(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e);
        }
    }
}
