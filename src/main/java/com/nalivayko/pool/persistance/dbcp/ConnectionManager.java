package com.nalivayko.pool.persistance.dbcp;

import java.sql.Connection;

public interface ConnectionManager {

    public Connection getConnection();

}
