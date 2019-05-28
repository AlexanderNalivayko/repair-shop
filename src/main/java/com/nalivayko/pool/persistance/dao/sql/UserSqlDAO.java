package com.nalivayko.pool.persistance.dao.sql;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.UserDAO;
import com.nalivayko.pool.persistance.dao.sql.query.UserQuery;
import com.nalivayko.pool.persistance.mappers.UserMapper;

import java.util.List;

public class UserSqlDAO extends AbstractSqlDAO<User> implements UserDAO {

    public UserSqlDAO(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public int create(User user, int contactsId) {
        return create(UserQuery.INSERT, preparedStatement -> {
            preparedStatement.setString(1, user.getUserRole().toString());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPhone());
        });
    }

    @Override
    public User findByUsername(String username) {
        return find(UserQuery.SELECT_BY_USERNAME, preparedStatement ->
                preparedStatement.setString(1, username), new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return findAll(UserQuery.SELECT_ALL, new UserMapper());
    }

    @Override
    public boolean update(User user) {
        return updateDelete(UserQuery.UPDATE_BY_ID, preparedStatement -> {
            preparedStatement.setString(1, user.getUserRole().toString());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPhone());
            preparedStatement.setInt(8, user.getId());
        });
    }

    @Override
    public boolean delete(int userId) {
        return updateDelete(UserQuery.DELETE_BY_ID, preparedStatement -> preparedStatement.setInt(1, userId));
    }
}
