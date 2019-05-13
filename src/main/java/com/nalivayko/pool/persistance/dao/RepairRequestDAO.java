package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairStatus;

import java.util.List;

public interface RepairRequestDAO {

    public int create(RepairRequest repairRequest);

    public List<RepairRequest> findByUserId(int UserId);

    public List<RepairRequest> findByStatus(RepairStatus status);

    public List<RepairRequest> findAll();

    public boolean update(RepairRequest repairRequest);

    public boolean delete(int id);
}
