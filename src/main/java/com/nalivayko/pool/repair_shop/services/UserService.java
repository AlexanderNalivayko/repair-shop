package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.User;

public interface UserService {

    User create(User user);

    User validate(String username, String pass);

    User getUserByUsername(String username);

}
