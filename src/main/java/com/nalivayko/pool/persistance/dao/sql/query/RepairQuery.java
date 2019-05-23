package com.nalivayko.pool.persistance.dao.sql.query;

public class RepairQuery {

    public static final String TABLE_NAME = "repair_requests";

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ITEM_ID = "item_id";
    public static final String STATUS = "status";
    public static final String CREATION_DATE = "creation_date";
    public static final String DESCRIPTION = "description";
    public static final String COST = "cost";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + USER_ID + ", "
            + ITEM_ID + ", "
            + STATUS + ", "
            + CREATION_DATE + ", "
            + DESCRIPTION + ", "
            + COST
            + ") "
            + " VALUES (?, ?, ?, NOW(), ?, ?)";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME
            + " INNER JOIN " + ItemQuery.TABLE_NAME
            + " ON "
            + ItemQuery.TABLE_NAME + "." + ItemQuery.ID
            + " = "
            + TABLE_NAME + "." + ITEM_ID
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

    public static final String SELECT_BY_USER_ID = SELECT_ALL
            + " WHERE "
            + USER_ID + " = ?";

    public static final String SELECT_BY_STATUS = SELECT_ALL
            + " WHERE "
            + STATUS + " LIKE ?";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET"
            + USER_ID + " = ?, "
            + ITEM_ID + " = ?, "
            + STATUS + " = ?, "
            + DESCRIPTION + " = ?, "
            + COST + " = ? "
            + " WHERE "
            + ID + " = ?";

    public static final String DELETE_BY_ID = "DELETE FROM TABLE " + TABLE_NAME
            + "WHERE "
            + ID + " = ?";

    private RepairQuery() {
    }
}
