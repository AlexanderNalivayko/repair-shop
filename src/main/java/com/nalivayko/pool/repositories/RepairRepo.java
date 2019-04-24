package com.nalivayko.pool.repositories;

import com.nalivayko.pool.domain.Repair;

import java.util.List;

public interface RepairRepo {

    public void add(Repair repair);

    public List<Repair> findByUserId(int UserId);

    public List<Repair> findAll();

    public void update(Repair repair);

    public void delete(int id);
}
