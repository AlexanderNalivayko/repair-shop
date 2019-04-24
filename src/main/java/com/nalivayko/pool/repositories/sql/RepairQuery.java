package com.nalivayko.pool.repositories.sql;

public class RepairQuery {

    public static final String TABLE_NAME = "repairRequests";

    public static final String INSERT_REPAIR_REQUEST = "INSERT INTO " + TABLE_NAME
            + " (id, userId, status, item, description, pictureUrl, price, managerNote, userFeedback)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public static final String SELECT_BY_USER_ID = SELECT_ALL + " WHERE userId = ?";

    public static final String DELETE_BY_ID = "DELETE FROM TABLE " + TABLE_NAME
            + "WHERE id = ?";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET"
            + " userId = ?,"
            + " status = ?, "
            + " item = ?, "
            + " description = ?,"
            + " pictureUrl = ?,"
            + " price = ?,"
            + " managerNote = ?,"
            + " userFeedback = ?"
            + " WHERE id = ?";
}
