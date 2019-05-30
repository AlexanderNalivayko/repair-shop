package com.nalivayko.pool.persistance;

import com.nalivayko.pool.exceptions.InternalAppException;
import com.nalivayko.pool.persistance.dbcp.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DefaultTransactionManager implements TransactionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTransactionManager.class);

    private ThreadLocal<Connection> threadConnection = new ThreadLocal<>();
    private ConnectionManager connectionManager;

    public DefaultTransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * can be used in dao to get same connection that was used to start transaction
     * @return thread-local connection
     */
    @Override
    public Connection getConnection() {
        createConnection();
        return threadConnection.get();
    }

    /**
     * should be called after dao operation that was made without transaction
     */
    @Override
    public void closeConnection() {
        try {
            Connection connection = threadConnection.get();
            if (connection != null) {
                connection.close();
            }
            threadConnection.remove();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * crate connection (so u don't need to call getConnection() before this method)
     * and starts transaction that should be ended by calling endTransaction() method
     */
    @Override
    public void startTransaction() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setSavepoint();
        } catch (SQLException e) {
            InternalAppException exception = new InternalAppException("Can't start transaction.");
            LOGGER.error(exception.getMessage());
            throw exception;
        }
    }

    /**
     * If all executions runs without exceptions - commit all changes that was made after
     * calling startTransaction(), otherwise - roll back all changes to the point
     * right before you call startTransaction().
     */
    @Override
    public void endTransaction() {
        Connection connection = getConnection();
        if (connection == null) {
            InternalAppException exception = new InternalAppException("Can't end transaction. Connection is null.");
            LOGGER.error(exception.getMessage());
            throw exception;
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            InternalAppException commitException = new InternalAppException("Can't commit changes to db.");
            LOGGER.error("Rolling back.", commitException);
            try {
                connection.rollback();
                LOGGER.info("Commit successfully rolled back");
            } catch (SQLException e1) {
                InternalAppException rollBackException = new InternalAppException("Can't rollback changes");
                LOGGER.error(rollBackException.getMessage());
                throw rollBackException;
            }
            throw commitException;
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    /**
     * create connection and right it to thread-local if such is not exist
     */
    private void createConnection() {
        if (threadConnection.get() == null) {
            threadConnection.set(connectionManager.getConnection());
        }
    }
}
