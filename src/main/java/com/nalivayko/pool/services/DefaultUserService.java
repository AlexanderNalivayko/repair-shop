package com.nalivayko.pool.services;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.UserDAO;

//todo pass encryption/decryption

/**
 * Provides methods to work with User (create, validate, find ...)
 */
public class DefaultUserService implements UserService {
    private UserDAO userDAO;
    private TransactionManager transactionManager;

    public DefaultUserService(UserDAO userDAO, TransactionManager transactionManager) {
        this.userDAO = userDAO;
        this.transactionManager = transactionManager;
    }

    /**
     * Create and save user
     * @param username - users username
     * @param pass - users pass
     * @param firstName - users firstname
     * @param lastName - users lastname
     * @param email - users email
     * @param phone - users phone
     */
    @Override
    public void create(String username, String pass,
                       String firstName, String lastName, String email, String phone) {
        try {
            User user = new User(UserRole.CUSTOMER, username, pass, firstName, lastName, email, phone);
            transactionManager.getConnection();
            userDAO.create(user);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Check if user exists and if it`s password equal to parameter pass
     * @param username - user you want to check
     * @param pass - pass of the user
     * @return User with username that was passed in parameters.
     *         or null - if user not exists of if password is not correct
     */
    @Override
    public User validate(String username, String pass) {
        try {
            transactionManager.getConnection();
            User user = userDAO.findByUsername(username);
            if (user == null || !user.getPassword().equals(pass)) {
                return null;
            }
            return user;
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Find user by it`s username
     * @param username - username of user you want to find
     * @return user with passed username
     */
    @Override
    public User getUserByUsername(String username) {
        try {
            transactionManager.getConnection();
            return userDAO.findByUsername(username);
        }finally {
            transactionManager.closeConnection();
        }
    }
}
