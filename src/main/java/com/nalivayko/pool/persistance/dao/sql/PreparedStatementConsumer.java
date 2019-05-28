package com.nalivayko.pool.persistance.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementConsumer {

    void prepare(PreparedStatement preparedStatement) throws SQLException;
}
