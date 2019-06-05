package com.nalivayko.pool.services;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;

import java.util.List;

public interface RepairRequestService {

    void createRepairRequest(User user, String itemType, String itemBrand, String itemName, String description);

    List<RepairRequest> getAllByUserId(int userId, int limit, int offset);

    List<RepairRequest> getAllWithStatus(RepairRequestStatus repairRequestStatus, int limit, int offset);

    List<RepairRequest> getAllByReviewAndRequestStatus(ReviewStatus reviewStatus,
                                                       RepairRequestStatus repairRequestStatus, int limit, int offset);

    void acceptRepairRequest(int repairRequestId, Integer cost);

    void rejectRepairRequest(int repairRequestId, String reason);

    void updateStatus(int repairRequestId, RepairRequestStatus repairRequestStatus);

    int countRequestsWithUserId(int UserId);

    int countRequestsWithStatus(RepairRequestStatus status);

    int countRequestsWithStatus(ReviewStatus reviewStatus,
                                RepairRequestStatus repairRequestStatus);
}
