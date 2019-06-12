package com.nalivayko.pool.repair_shop.persistance.dbcp;

import com.nalivayko.pool.repair_shop.exceptions.AppInitialisationException;
import com.nalivayko.pool.repair_shop.exceptions.InternalAppException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static com.nalivayko.pool.repair_shop.util.PropertiesUtil.getProperty;

/**
 * {@code MySqlConnectionManager} Manage dbcp
 */
public class MySqlConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlConnectionManager.class);
    private static final String PROP_FILE_NAME = "dbcp";

    private String url;
    private String username;
    private String pass;
    private int initialSize;
    private int maxSize;

    private static BasicDataSource instance;

    public MySqlConnectionManager() {
        try {
            url = getProperty("url", PROP_FILE_NAME);
            username = getProperty("username", PROP_FILE_NAME);
            pass = getProperty("pass", PROP_FILE_NAME);
            initialSize = Integer.parseInt(getProperty("initial", PROP_FILE_NAME));
            maxSize = Integer.parseInt(getProperty("max", PROP_FILE_NAME));
            instance = setupDriver();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new AppInitialisationException("Can't start DBCP");
        }
    }

    /**
     * provide connection from dbcp
     * @return Connection
     */
    public synchronized Connection getConnection() {
        try {
            return instance.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Can't provide connection from pool.");
            throw new InternalAppException(e);
        }
    }

    /**
     * Initialise dbcp
     */
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
