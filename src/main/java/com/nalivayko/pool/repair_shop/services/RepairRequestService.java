package com.nalivayko.pool.services;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.User;

import java.util.List;

public interface RepairRequestService {

    void createRepairRequest(User user, String itemType, String itemBrand, String itemName, String description);

    List<RepairRequest> getAllByUserId(int userId, int limit, int offset);

    List<RepairRequest> getAllNew(int limit, int offset);

    List<RepairRequest> getAllAccepted(int limit, int offset);

    void acceptRepairRequest(int repairRequestId, Integer cost);

    void rejectRepairRequest(int repairRequestId, String reason);

    void performRepairRequest(int repairRequestId);

    int countByUserId(int UserId);

    int countNew();

    int countAccepted();
}
