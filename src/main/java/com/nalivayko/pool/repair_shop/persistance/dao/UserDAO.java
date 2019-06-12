package com.nalivayko.pool.repair_shop.persistance.dao;

import com.nalivayko.pool.repair_shop.model.User;

import java.util.List;

public interface UserDAO {

    int create(User user);

    User findByUsername(String email);

    List<User> findAll();

    boolean update(User user);

    boolean delete(int id);
}
