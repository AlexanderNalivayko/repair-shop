package com.nalivayko.pool.repair_shop.persistance.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementConsumer {

    void prepare(PreparedStatement preparedStatement) throws SQLException;
}
