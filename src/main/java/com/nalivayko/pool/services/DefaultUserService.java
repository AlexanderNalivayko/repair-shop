package com.nalivayko.pool.services;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.persistance.dao.UserDAO;

public class DefaultUserService implements UserService {

    private UserDAO userDAO;

    public DefaultUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //todo encrypting
    @Override
    public void create(String username, String pass,
                       String firstName, String lastName, String email, String phone) {
        User user = new User(UserRole.CUSTOMER, username, pass, firstName, lastName, email, phone);
        userDAO.create(user);
    }

    //todo encrypting
    @Override
    public User validate(String username, String pass) {
        User user = userDAO.findByUsername(username);
        if (user == null || !user.getPassword().equals(pass)) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
