package com.nalivayko.pool.repair_shop.persistance.dao;

import com.nalivayko.pool.repair_shop.model.Item;

public interface ItemDAO {

    int create(Item item);

    boolean update(Item item);
}
