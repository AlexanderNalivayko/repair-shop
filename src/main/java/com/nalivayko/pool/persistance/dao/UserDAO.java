package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.User;

import java.util.List;

public interface UserDAO {

    public int create(User user);

    public User findByEmail(String email);

    public List<User> findAll();

    public boolean update(User user);

    public boolean delete(int id);
}
