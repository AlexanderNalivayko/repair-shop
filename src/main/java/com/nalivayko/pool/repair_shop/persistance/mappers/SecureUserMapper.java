package com.nalivayko.pool.persistance.mappers;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.persistance.dao.sql.query.UserQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User mapper that maps all data except of password
 */
public class SecureUserMapper implements Mapper<User> {

    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new User(resultSet.getInt(UserQuery.ID),
                UserRole.valueOf(resultSet.getString(UserQuery.ROLE)),
                resultSet.getString(UserQuery.USERNAME),
                resultSet.getString(UserQuery.FIRST_NAME),
                resultSet.getString(UserQuery.LAST_NAME),
                resultSet.getString(UserQuery.EMAIL),
                resultSet.getString(UserQuery.PHONE));
    }
}
