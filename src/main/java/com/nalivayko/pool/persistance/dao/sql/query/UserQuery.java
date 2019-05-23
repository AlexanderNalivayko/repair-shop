package com.nalivayko.pool.persistance.dao.sql.query;

public class UserQuery {

    public static final String TABLE_NAME = "users";

    public static final String ID = "id";
    public static final String CONTACTS_ID = "contacts_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String ROLE = "role";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME
            + " INNER JOIN " + ContactsQuery.TABLE_NAME
            + " ON "
            + ContactsQuery.TABLE_NAME + "." + ContactsQuery.ID
            + " = "
            + TABLE_NAME + "." + CONTACTS_ID;

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + CONTACTS_ID + ","
            + USERNAME + ","
            + PASSWORD + ","
            + FIRST_NAME + ","
            + LAST_NAME + ","
            + ROLE
            + ") "
            + " VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SELECT_BY_USERNAME = SELECT_ALL
            + " WHERE "
            + USERNAME
            +" LIKE ?";

    public static final String DELETE_BY_ID = "DELETE FROM TABLE " + TABLE_NAME
            + "WHERE "
            + ID + " = ?";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET"
            + CONTACTS_ID + " = ?"
            + USERNAME + " = ?, "
            + PASSWORD + " = ?, "
            + FIRST_NAME + " = ?, "
            + LAST_NAME + " = ?, "
            + ROLE + " = ?, "
            + " WHERE "
            + ID + " = ?";

    private UserQuery() {
    }
}
