package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.Contacts;

public interface ContactsDAO {

    public int create(Contacts contacts);

    public boolean update(Contacts contacts);
}
