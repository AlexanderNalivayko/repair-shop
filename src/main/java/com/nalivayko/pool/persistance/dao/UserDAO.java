package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.User;

import java.util.List;

public interface UserDAO {

    int create(User user);

    User findByUsername(String email);

    List<User> findAll();

    boolean update(User user);

    boolean delete(int id);
}
