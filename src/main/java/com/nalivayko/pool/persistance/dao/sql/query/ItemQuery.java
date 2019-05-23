package com.nalivayko.pool.persistance.dao.sql.query;

public class ItemQuery {

    public static final String TABLE_NAME = "items";

    public static final String ID = "id";
    public static final String PRODUCT_TYPE = "product_type";
    public static final String BRAND = "brand";
    public static final String PRODUCT_NAME = "product_name";

    public static final String INSERT = "INSERT INTO TABLE " + TABLE_NAME
            + " ("
            + PRODUCT_TYPE + ", "
            + BRAND + ", "
            + PRODUCT_NAME
            + ") "
            + "VALUES (?, ?, ?)";

    public static final String UPDATE = "UPDATE " + TABLE_NAME
            + " SET"
            + PRODUCT_TYPE + " = ?, "
            + BRAND + " = ?, "
            + PRODUCT_NAME + " = ? "
            + " WHERE "
            + ID + " = ?";
}
