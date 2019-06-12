package com.nalivayko.pool.repair_shop.persistance.dao.sql.query;

public class UserQuery {

    public static final String TABLE_NAME = "users";

    public static final String ID = "id";
    public static final String ROLE = "role";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + ROLE + ", "
            + USERNAME + ", "
            + PASSWORD + ", "
            + FIRST_NAME + ", "
            + LAST_NAME + ", "
            + EMAIL + ", "
            + PHONE
            + ") "
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public static final String SELECT_BY_USERNAME = SELECT_ALL
            + " WHERE "
            + USERNAME
            +" LIKE ?";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET "
            + ROLE + " = ?, "
            + USERNAME + " = ?, "
            + PASSWORD + " = ?, "
            + FIRST_NAME + " = ?, "
            + LAST_NAME + " = ?, "
            + EMAIL + " = ?, "
            + PHONE + " = ?"
            + " WHERE "
            + ID + " = ?";

    public static final String DELETE_BY_ID = "DELETE FROM TABLE " + TABLE_NAME
            + "WHERE "
            + ID + " = ?";

    private UserQuery() {
    }
}
