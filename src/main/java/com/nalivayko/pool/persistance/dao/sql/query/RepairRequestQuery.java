package com.nalivayko.pool.persistance.dao.sql.query;

public class RepairRequestQuery {

    public static final String TABLE_NAME = "repair_requests";

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ITEM_ID = "item_id";
    public static final String REVIEW_ID = "review_id";
    public static final String STATUS = "status";
    public static final String CREATION_TIME = "creation_time";
    public static final String DESCRIPTION = "description";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + USER_ID + ", "
            + ITEM_ID + ", "
            + STATUS + ", "
            + DESCRIPTION
            + ") "
            + " VALUES (?, ?, ?, ?)";

    private static final String LIMIT = " LIMIT ? OFFSET ?";

    private static final String ORDER_BY = " ORDER BY UNIX_TIMESTAMP(" + CREATION_TIME + ") DESC";

    private static final String JOIN_USER = " LEFT JOIN " + UserQuery.TABLE_NAME
            + " ON "
            + UserQuery.TABLE_NAME + "." + UserQuery.ID
            + " = "
            + TABLE_NAME + "." + USER_ID;

    private static final String JOIN_ITEM = " LEFT JOIN " + ItemQuery.TABLE_NAME
            + " ON "
            + ItemQuery.TABLE_NAME + "." + ItemQuery.ID
            + " = "
            + TABLE_NAME + "." + ITEM_ID;

    private static final String JOIN_REVIEW = " LEFT JOIN " + ReviewQuery.TABLE_NAME
            + " ON "
            + ReviewQuery.TABLE_NAME + "." + ReviewQuery.ID
            + " = "
            + TABLE_NAME + "." + REVIEW_ID;

    private static final String GENERAL_SELECT_ALL = "SELECT "
            + TABLE_NAME + "." + ID + ", "
            + UserQuery.TABLE_NAME + "." + UserQuery.ROLE + ", "
            + TABLE_NAME + "." + USER_ID + ", "
            + UserQuery.TABLE_NAME + "." + UserQuery.USERNAME + ", "
            + UserQuery.TABLE_NAME + "." + UserQuery.FIRST_NAME + ", "
            + UserQuery.TABLE_NAME + "." + UserQuery.LAST_NAME + ", "
            + UserQuery.TABLE_NAME + "." + UserQuery.EMAIL + ", "
            + UserQuery.TABLE_NAME + "." + UserQuery.PHONE + ", "
            + TABLE_NAME + "." + ITEM_ID + ", "
            + ItemQuery.TABLE_NAME + "." + ItemQuery.ITEM_TYPE + ", "
            + ItemQuery.TABLE_NAME + "." + ItemQuery.BRAND + ", "
            + ItemQuery.TABLE_NAME + "." + ItemQuery.ITEM_NAME + ", "
            + TABLE_NAME + "." + REVIEW_ID + ", "
            + ReviewQuery.TABLE_NAME + "." + ReviewQuery.REVIEW_STATUS + ", "
            + ReviewQuery.TABLE_NAME + "." + ReviewQuery.REVIEW_TIME + ", "
            + ReviewQuery.TABLE_NAME + "." + ReviewQuery.COST + ", "
            + TABLE_NAME + "." + STATUS + ", "
            + TABLE_NAME + "." + CREATION_TIME + ", "
            + TABLE_NAME + "." + DESCRIPTION
            + " FROM " + TABLE_NAME
            + JOIN_USER
            + JOIN_ITEM
            + JOIN_REVIEW;

    public static final String SELECT_BY_USER_ID_WITH_LIMIT = GENERAL_SELECT_ALL
            + " WHERE "
            + USER_ID + " = ?"
            + ORDER_BY
            + LIMIT;

    public static final String SELECT_BY_STATUS_WITH_LIMIT = GENERAL_SELECT_ALL
            + " WHERE "
            + STATUS + " LIKE ?"
            + ORDER_BY
            + LIMIT;

    private static final String SELECT_BY_REVIEW_AND_REQUEST_STATUS = GENERAL_SELECT_ALL
            + " WHERE "
            + ReviewQuery.REVIEW_STATUS + " LIKE ? "
            + " AND "
            + STATUS + " LIKE ?"
            + ORDER_BY;

    public static final String SELECT_BY_REVIEW_AND_REQUEST_STATUS_WITH_LIMIT =
            SELECT_BY_REVIEW_AND_REQUEST_STATUS + LIMIT;

    private static final String COUNT = "SELECT COUNT(*) FROM " + TABLE_NAME;

    public static final String COUNT_BY_USER = COUNT
            + " WHERE " + USER_ID + " = ?";

    public static final String COUNT_BY_STATUS = COUNT
            + " WHERE " + STATUS + " = ?";

    public static final String COUNT_BY_REPAIR_AND_REVIEW_STATUS =
            "SELECT COUNT(" + ID + ") FROM ( "
                    + SELECT_BY_REVIEW_AND_REQUEST_STATUS
                    + " ) as A";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET "
            + USER_ID + " = ?, "
            + ITEM_ID + " = ?, "
            + REVIEW_ID + " = ?, "
            + STATUS + " = ?, "
            + DESCRIPTION + " = ?"
            + " WHERE "
            + ID + " = ?";

    public static final String UPDATE_REVIEW = "UPDATE " + TABLE_NAME
            + " SET "
            + REVIEW_ID + " = ? "
            + " WHERE "
            + ID + " = ?";

    public static final String UPDATE_STATUS = "UPDATE " + TABLE_NAME
            + " SET "
            + STATUS + " = ? "
            + " WHERE "
            + ID + " = ?";

    public static final String DELETE_BY_ID = "DELETE FROM TABLE " + TABLE_NAME
            + "WHERE "
            + ID + " = ?";

    private RepairRequestQuery() {
    }
}
