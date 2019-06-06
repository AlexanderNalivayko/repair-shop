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

    /**
     * Take sqlQuery and return first row, first cell result. Normally it should return countWithUser of rows
     * in database if query looks like SELECT COUNT(*) FROM TABLE_NAME
     * @param sqlQuery - countWithUser sql query
     * @return countWithUser or rows in table (if sqlQuery - is right)
     */
    protected int count(String sqlQuery){
        Connection connection = transactionManager.getConnection();
        try (ResultSet resultSet =
                     connection.createStatement().executeQuery(sqlQuery)) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e.getMessage());
        }
    }

    /**
     * pretty mach the same as {@link #count} but has PreparedStatementConsumer
     * which means that u can pass sql query with parameters and set them.
     * @param sqlQuery - COUNT query with parameters
     * @param preparedStatementConsumer - specific preparedStatementConsumer that sets parameters of query
     * @return count of rows
     */
    protected int count(String sqlQuery, PreparedStatementConsumer preparedStatementConsumer){
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatementConsumer.prepare(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e);
        }
    }

    /**
     * Get record from db that matches to input parameters and map it to specific object with mapper.
     * Sql parameter - should be SELECT sql query
     * @param sql - SELECT query
     * @param preparedStatementConsumer - specific preparedStatementConsumer that sets parameters of query
     * @param mapper - to map data from result set to specific object
     * @return - object (type depends from mapper)
     */
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

    /**
     * Get List of records from db that matches to input parameters and map it to specific object with mapper
     * sql parameter - should be SELECT sql query
     * @param query - SELECT query
     * @param mapper - to map data from result set to specific object
     * @return List of records
     */
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

    /**
     * Same as {@link com.nalivayko.pool.persistance.dao.sql.AbstractSqlDAO#findAll(String, Mapper)}}
     * But has PreparedStatementConsumer which should be used to set parameters of query
     */
    protected List<T> findAll(String query, PreparedStatementConsumer preparedStatementConsumer, Mapper<T> mapper) {
        Connection connection = transactionManager.getConnection();
        List<T> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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

    /**
     * This method can be used fo update or delete data from db depending on the query parameter
     * @param query - delete or update sql query
     * @param preparedStatementConsumer - specific preparedStatementConsumer that sets parameters of query
     * @return - true if operation successful, false - if not
     */
    protected boolean updateOrDelete(String query, PreparedStatementConsumer preparedStatementConsumer) {
        Connection connection = transactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatementConsumer.prepare(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new InternalAppException(e);
        }
    }
}
