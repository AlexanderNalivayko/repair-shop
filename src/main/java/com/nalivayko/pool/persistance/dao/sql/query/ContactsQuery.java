package com.nalivayko.pool.persistance.dao.sql.query;

public class ContactsQuery {

    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    public static final String TABLE_NAME = "contacts";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " ("
            + EMAIL + ", "
            + PHONE
            + ") "
            + " VALUES (?, ?)";

    public static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME
            + " SET"
            + EMAIL + " = ?, "
            + PHONE + " = ? "
            + " WHERE "
            + ID + " = ?";

    private ContactsQuery() {
    }
}
