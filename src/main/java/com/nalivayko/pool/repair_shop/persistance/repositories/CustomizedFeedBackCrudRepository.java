package com.nalivayko.pool.repair_shop.persistance.repositories;

import com.nalivayko.pool.repair_shop.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomizedFeedBackCrudRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
