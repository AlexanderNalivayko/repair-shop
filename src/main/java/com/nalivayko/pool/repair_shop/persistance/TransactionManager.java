package com.nalivayko.pool.repair_shop.persistance;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionManager {

    Connection getConnection();

    void closeConnection();

    void startTransaction();

    void endTransaction();
}
