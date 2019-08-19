package com.nalivayko.pool.repair_shop.persistance.repositories;

import com.nalivayko.pool.repair_shop.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedUserCrudRepository extends PagingAndSortingRepository<User, Integer> {

    User findByUsername(String username);
}
