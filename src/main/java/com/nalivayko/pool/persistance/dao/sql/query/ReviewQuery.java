package com.nalivayko.pool.persistance.dao.sql.query;

public class ReviewQuery {

    public static final String TABLE_NAME = "reviews";

    public static final String ID = "id";
    public static final String STATUS = "status";
    public static final String REVIEW_TIME = "review_time";
    public static final String COST = "cost";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + STATUS + ", "
            + COST
            + ") "
            + " VALUES (?, ?)";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET "
            + STATUS + " = ?, "
            + COST + " = ? "
            + " WHERE "
            + ID + " = ?";

    private ReviewQuery() {
    }
}
