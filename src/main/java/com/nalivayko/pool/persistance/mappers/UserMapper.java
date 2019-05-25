package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.persistance.dao.sql.query.UserQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new User(resultSet.getInt(UserQuery.ID),
                resultSet.getString(UserQuery.USERNAME),
                resultSet.getString(UserQuery.PASSWORD),
                resultSet.getString(UserQuery.FIRST_NAME),
                resultSet.getString(UserQuery.LAST_NAME),
                UserRole.valueOf(resultSet.getString(UserQuery.ROLE)),
                new ContactsMapper().getEntity(resultSet));
    }
}