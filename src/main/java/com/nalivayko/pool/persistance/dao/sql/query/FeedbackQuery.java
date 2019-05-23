package com.nalivayko.pool.persistance.dao.sql.query;

public class FeedbackQuery {

    private static final String TABLE_NAME = "feedback";

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String TEXT = "text";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + USER_ID + ", "
            + TEXT + "text"
            + ") "
            + " VALUES (?, ?)";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME
            + " INNER JOIN " + UserQuery.TABLE_NAME
            + " ON "
            + UserQuery.TABLE_NAME + "." + UserQuery.ID
            + " = "
            + TABLE_NAME + "." + USER_ID
            + " INNER JOIN " + ContactsQuery.TABLE_NAME
            + " ON "
            + ContactsQuery.TABLE_NAME + "." + ContactsQuery.ID
            + " = "
            + UserQuery.TABLE_NAME + "." + UserQuery.CONTACTS_ID;

    public static final String DELETE_BY_ID = "DELETE FROM TABLE " + TABLE_NAME
            + "WHERE "
            + ID + " = ?";

    private FeedbackQuery() {
    }
}
