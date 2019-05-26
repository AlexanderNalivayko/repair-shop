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
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword()); //todo encrypting!!!!
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getUserRole().toString());
            preparedStatement.setInt(6, contactsId);
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
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getUserRole().toString());
            preparedStatement.setInt(6, user.getContacts().getId());
            preparedStatement.setInt(7, user.getId());
        });
    }

    @Override
    public boolean delete(int id) {
        return updateDelete(UserQuery.DELETE_BY_ID, preparedStatement -> preparedStatement.setInt(1, id));
    }
}
