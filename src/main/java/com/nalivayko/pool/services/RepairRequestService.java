package com.nalivayko.pool.services;

import com.nalivayko.pool.model.RepairRequest;

import java.util.List;

public interface RepairRequestService {

    public List<RepairRequest> getRepairRequestsByUserId(int userId);



}
