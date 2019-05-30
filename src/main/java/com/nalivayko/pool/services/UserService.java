package com.nalivayko.pool.services;

import com.nalivayko.pool.model.User;

public interface UserService {

    public void create(String username, String pass, String firstName, String lastName,
                       String email, String phone);

    public User validate(String username, String pass);

    public User getUserByUsername(String username);

}
