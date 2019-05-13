package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.Contacts;
import com.nalivayko.pool.persistance.dao.sql.query.ContactsQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsMapper implements Mapper<Contacts> {

    @Override
    public Contacts getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new Contacts(resultSet.getInt(ContactsQuery.ID),
                resultSet.getString(ContactsQuery.EMAIL),
                resultSet.getString(ContactsQuery.PHONE));
    }
}
