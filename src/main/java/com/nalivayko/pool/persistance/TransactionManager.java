package com.nalivayko.pool.persistance;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionManager {

    Connection getConnection();

    void closeConnection();

    void startTransaction() throws SQLException;

    void endTransaction() throws SQLException;
}
