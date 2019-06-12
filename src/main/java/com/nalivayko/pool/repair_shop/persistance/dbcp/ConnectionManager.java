package com.nalivayko.pool.repair_shop.persistance.dbcp;

import java.sql.Connection;

public interface ConnectionManager {

    Connection getConnection();
}
