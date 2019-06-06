package com.nalivayko.pool.persistance.dao.sql.query;

public class ItemQuery {

    public static final String TABLE_NAME = "items";

    public static final String ID = "id";
    public static final String ITEM_TYPE = "item_type";
    public static final String BRAND = "brand";
    public static final String ITEM_NAME = "item_name";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + ITEM_TYPE + ", "
            + BRAND + ", "
            + ITEM_NAME
            + ") "
            + "VALUES (?, ?, ?)";

    public static final String UPDATE = "UPDATE " + TABLE_NAME
            + " SET "
            + ITEM_TYPE + " = ?, "
            + BRAND + " = ?, "
            + ITEM_NAME + " = ? "
            + " WHERE "
            + ID + " = ?";
}
