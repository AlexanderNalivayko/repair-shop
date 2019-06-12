package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.Item;

public interface ItemDAO {

    int create(Item item);

    boolean update(Item item);
}
