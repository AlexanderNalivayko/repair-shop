package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.Item;

public interface ItemDAO {

    public int create(Item item);

    public boolean update(Item item);
}
