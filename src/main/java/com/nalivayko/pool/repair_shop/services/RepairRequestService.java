package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.RepairRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepairRequestService {

    RepairRequest createRepairRequest(RepairRequestDto repairRequest, String username);

    Page<RepairRequest> getAllByUserName(String username, Pageable pageable);

    Page<RepairRequest> getAllNew(Pageable pageable);

    Page<RepairRequest> getAllAccepted(Pageable pageable);

    void acceptRepairRequest(Integer repairRequestId, Integer price);

    void rejectRepairRequest(Integer repairRequestId, String reason);

    void performRepairRequest(Integer repairRequestId);
}
