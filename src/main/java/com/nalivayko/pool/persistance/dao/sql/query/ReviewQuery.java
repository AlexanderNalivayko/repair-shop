package com.nalivayko.pool.persistance.dao.sql.query;

public class ReviewQuery {

    public static final String TABLE_NAME = "reviews";

    public static final String ID = "id";
    public static final String REVIEW_STATUS = "review_status";
    public static final String REVIEW_TIME = "review_time";
    public static final String COST = "cost";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + REVIEW_STATUS + ", "
            + COST
            + ") "
            + " VALUES (?, ?)";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET "
            + REVIEW_STATUS + " = ?, "
            + COST + " = ? "
            + " WHERE "
            + ID + " = ?";

    private ReviewQuery() {
    }
}
