package com.nalivayko.pool.services;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairStatus;

import java.util.List;

public interface RepairRequestService {

    public List<RepairRequest> getAll();

    public List<RepairRequest> getAllActive();

    public List<RepairRequest> getAllNew();

    public List<RepairRequest> getAllInactive();

    public void create(RepairStatus repairStatus, int accountId,
                                    String item, String description);

    public void update(int repairRequestId, RepairStatus repairStatus, int accountId,
                                    String item, String description);

}
