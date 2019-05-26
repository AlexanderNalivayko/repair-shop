package com.nalivayko.pool.services;

import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.UserRole;
import com.nalivayko.pool.persistance.dao.UserDAO;

public class DefaultUserService implements UserService {

    private UserDAO userDAO;

    public DefaultUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void create(String username, String firstName, String lastName, UserRole userRole, String email, String password) {

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
}
