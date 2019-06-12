package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.User;

public interface UserService {

    void create(String username, String pass, String firstName, String lastName,
                       String email, String phone);

    User validate(String username, String pass);

    User getUserByUsername(String username);

}
