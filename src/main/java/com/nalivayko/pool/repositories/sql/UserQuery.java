package com.nalivayko.pool.repositories.sql;

public class UserQuery {

    public static final String TABLE_NAME = "users";

    public static final String INSERT_USER = "INSERT INTO " + TABLE_NAME
            + " (id, firstName, lastName, userType, email, password)"
            + " VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public static final String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";

    public static final String SELECT_BY_EMAIL = SELECT_ALL + " WHERE email LIKE ?";

    public static final String DELETE_BY_ID = "DELETE FROM TABLE " + TABLE_NAME
            + "WHERE id = ?";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET"
            + " firstName = ?, "
            + " lastName = ?, "
            + " userType = ?,"
            + " email = ?,"
            + " password = ?,"
            + " WHERE id = ?";
}
