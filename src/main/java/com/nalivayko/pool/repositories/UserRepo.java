package com.nalivayko.pool.repositories;

import com.nalivayko.pool.domain.User;

public interface UserRepo {

    public void add(User user);

    public User findByEmail(String name);

    public User findById(int id);

    public void update(User user);

    public void delete(int id);
}
