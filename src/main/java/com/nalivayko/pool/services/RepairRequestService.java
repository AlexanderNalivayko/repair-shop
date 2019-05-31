package com.nalivayko.pool.services;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;

import java.util.List;

public interface RepairRequestService {

    public List<RepairRequest> getRepairRequestsByUserId(int userId);

    public void createRepairRequest(User user, String itemType, String itemBrand, String itemName, String description);

    public List<RepairRequest> getAllWithStatus(RepairRequestStatus repairRequestStatus);

    public List<RepairRequest> getAllByReviewAndRequestStatus(ReviewStatus reviewStatus,
                                                              RepairRequestStatus repairRequestStatus);

    public void updateReview(int repairRequestId, ReviewStatus reviewStatus, Integer cost);

    public void updateStatus(int repairRequestId, RepairRequestStatus repairRequestStatus);


}
