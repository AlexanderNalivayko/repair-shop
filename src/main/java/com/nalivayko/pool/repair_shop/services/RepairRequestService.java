package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.User;

import java.util.List;

public interface RepairRequestService {

    void createRepairRequest(User user, String itemType, String itemBrand, String itemName, String description);

    List<RepairRequest> getAllByUserId(int userId, int limit, int offset);

    List<RepairRequest> getAllNew(int limit, int offset);

    List<RepairRequest> getAllAccepted(int limit, int offset);

    void acceptRepairRequest(String repairRequestId, String cost);

    void rejectRepairRequest(int repairRequestId, String reason);

    void performRepairRequest(int repairRequestId);

    int countByUserId(int UserId);

    int countNew();

    int countAccepted();
}
