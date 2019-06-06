package com.nalivayko.pool.services;

import com.nalivayko.pool.model.User;

public interface UserService {

    void create(String username, String pass, String firstName, String lastName,
                       String email, String phone);

    User validate(String username, String pass);

    User getUserByUsername(String username);

}
