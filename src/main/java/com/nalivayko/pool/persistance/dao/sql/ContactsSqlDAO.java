package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.Contacts;
import com.nalivayko.pool.persistance.dao.ContactsDAO;
import com.nalivayko.pool.persistance.dbcp.ConnectionManager;
import com.nalivayko.pool.persistance.dao.sql.query.ContactsQuery;

public class ContactsSqlDAO extends AbstractSqlDAO<Contacts> implements ContactsDAO {

    public ContactsSqlDAO(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    public int create(Contacts contacts) {
        return create(ContactsQuery.INSERT, preparedStatement -> {
            preparedStatement.setString(1, contacts.getEmail());
            preparedStatement.setString(2, contacts.getPhone());
        });
    }

    @Override
    public boolean update(Contacts contacts) {
        return updateDelete(ContactsQuery.UPDATE_BY_ID, preparedStatement -> {
            preparedStatement.setString(1, contacts.getEmail());
            preparedStatement.setString(2, contacts.getPhone());
            preparedStatement.setInt(3, contacts.getId());
        });
    }
}
