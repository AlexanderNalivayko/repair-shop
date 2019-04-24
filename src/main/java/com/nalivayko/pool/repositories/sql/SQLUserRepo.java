package com.nalivayko.pool.repositories.sql;

import com.nalivayko.pool.domain.User;
import com.nalivayko.pool.domain.UserType;
import com.nalivayko.pool.exceptions.InternalAppException;
import com.nalivayko.pool.repositories.ConnectionManager;
import com.nalivayko.pool.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserRepo implements UserRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlConnectionManager.class);

    private ConnectionManager connectionManager;

    public SQLUserRepo(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void add(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UserQuery.INSERT_USER)) {
            preparedStatement.setObject(1, user.getId());
            preparedStatement.setObject(2, user.getFirstName());
            preparedStatement.setObject(3, user.getLastName());
            preparedStatement.setObject(4, user.getUserType().toString());
            preparedStatement.setObject(5, user.getEmail());
            preparedStatement.setObject(6, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't add user", e);
            throw new InternalAppException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        return findBy(UserQuery.SELECT_BY_EMAIL, email);
    }

    @Override
    public User findById(int id) {
        return findBy(UserQuery.SELECT_BY_ID, id);
    }

    private <T> User findBy(String query, T value) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, value);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find user", e);
            throw new InternalAppException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UserQuery.INSERT_USER)) {
            preparedStatement.setObject(1, user.getFirstName());
            preparedStatement.setObject(2, user.getLastName());
            preparedStatement.setObject(3, user.getUserType().toString());
            preparedStatement.setObject(4, user.getEmail());
            preparedStatement.setObject(5, user.getPassword());
            preparedStatement.setObject(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't update user", e);
            throw new InternalAppException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    private static User resultSetToUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt(1))
                .setFirstName(resultSet.getString(2))
                .setLastName(resultSet.getString(3))
                .setUserType(UserType.valueOf(resultSet.getString(4)))
                .setEmail(resultSet.getString(5))
                .setPassword(resultSet.getString(6)).build();
    }
}
