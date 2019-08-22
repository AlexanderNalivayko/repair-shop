package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepairRequestService {

    RepairRequest createRepairRequest(RepairRequest repairRequest);

    Page<RepairRequest> getAllByUserName(String username, Pageable pageable);

    Page<RepairRequest> getAllNew(Pageable pageable);

    Page<RepairRequest> getAllAccepted(Pageable pageable);

    void acceptRepairRequest(String repairRequestId, String cost);

    void rejectRepairRequest(int repairRequestId, String reason);

    void performRepairRequest(int repairRequestId);
}
