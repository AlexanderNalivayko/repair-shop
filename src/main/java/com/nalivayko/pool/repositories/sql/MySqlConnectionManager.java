package com.nalivayko.pool.repositories.sql;

import com.nalivayko.pool.exceptions.InternalAppException;
import com.nalivayko.pool.repositories.ConnectionManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static com.nalivayko.pool.util.PropertiesUtil.getProperty;

public class MySqlConnectionManager implements ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlConnectionManager.class);
    private static final String PROP_FILE_NAME = "dbcp";

    private final String url;
    private final String username;
    private final String pass;
    private final int initialSize;
    private final int maxSize;

    private BasicDataSource instance;

    {
        this.url = getProperty("url", PROP_FILE_NAME);
        this.username = getProperty("username", PROP_FILE_NAME);
        this.pass = getProperty("pass", PROP_FILE_NAME);
        this.initialSize = Integer.parseInt(getProperty("initial", PROP_FILE_NAME));
        this.maxSize = Integer.parseInt(getProperty("max", PROP_FILE_NAME));
        try {
            instance = setupDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MySqlConnectionManager() {
    }

    public synchronized Connection getConnection() {
        try {
            return instance.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Can't access database.");
            throw new InternalAppException(e);
        }
    }

    private BasicDataSource setupDriver() throws Exception {
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
