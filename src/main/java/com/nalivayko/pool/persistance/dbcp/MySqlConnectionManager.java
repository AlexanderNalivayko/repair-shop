package com.nalivayko.pool.persistance.dbcp;

import com.nalivayko.pool.exceptions.AppInitialisationException;
import com.nalivayko.pool.exceptions.InternalAppException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static com.nalivayko.pool.util.PropertiesUtil.getProperty;

public class MySqlConnectionManager implements ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlConnectionManager.class);
    private static final String PROP_FILE_NAME = "dbcp";

    private static final String url = getProperty("url", PROP_FILE_NAME);
    private static final String username = getProperty("username", PROP_FILE_NAME);
    private static final String pass = getProperty("pass", PROP_FILE_NAME);
    private static final int initialSize = Integer.parseInt(getProperty("initial", PROP_FILE_NAME));
    private static final int maxSize = Integer.parseInt(getProperty("max", PROP_FILE_NAME));

    private static BasicDataSource instance;

    public MySqlConnectionManager() {
        try {
            instance = setupDriver();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new AppInitialisationException("Can't start DBCP");
        }
    }

    public synchronized Connection getConnection() {
        try {
            return instance.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Can't access database.");
            throw new InternalAppException(e);
        }
    }

    private static BasicDataSource setupDriver() throws Exception {
        BasicDataSource dbcp = new BasicDataSource();
        dbcp.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbcp.setUrl(url);
        dbcp.setUsername(username);
        dbcp.setPassword(pass);
        dbcp.setInitialSize(initialSize);
        dbcp.setMaxTotal(maxSize);
        dbcp.getConnection();
        return dbcp;
    }
}
