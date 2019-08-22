package com.nalivayko.pool.repair_shop.persistance.repositories;

import com.nalivayko.pool.repair_shop.model.Item;
import com.nalivayko.pool.repair_shop.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedReviewCrudRepository extends CrudRepository<Review, Integer> {
}
