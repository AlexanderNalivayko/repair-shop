package com.nalivayko.pool.repair_shop.persistance.dao.sql.query;

public class FeedbackQuery {

    private static final String TABLE_NAME = "feedback";

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String TEXT = "text";
    public static final String CREATION_TIME = "creation_time";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + USER_ID + ", "
            + TEXT
            + ") "
            + " VALUES (?, ?)";

    public static final String COUNT = "SELECT COUNT(*) FROM " + TABLE_NAME;

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME
            + " INNER JOIN " + UserQuery.TABLE_NAME
            + " ON "
            + UserQuery.TABLE_NAME + "." + UserQuery.ID
            + " = "
            + TABLE_NAME + "." + USER_ID
            + " ORDER BY " + TABLE_NAME + "." + ID + " DESC";

    public static final String SELECT_ALL_WITH_LIMIT = SELECT_ALL + " LIMIT ? OFFSET ?";

    public static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME
            + " WHERE "
            + ID + " = ?";

    private FeedbackQuery() {
    }
}
