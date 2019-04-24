package com.nalivayko.pool.repositories;

import java.sql.Connection;

public interface ConnectionManager {

    public Connection getConnection();

}
