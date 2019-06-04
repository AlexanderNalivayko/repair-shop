package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;

import java.util.List;

public interface RepairRequestDAO {

    int create(RepairRequest repairRequest);

    List<RepairRequest> findByUserId(int UserId, int limit, int offset);

    List<RepairRequest> findByRepairRequestStatus(RepairRequestStatus status, int limit, int offset);

    List<RepairRequest> findByReviewAndRequestStatus(ReviewStatus reviewStatus,
                                                     RepairRequestStatus repairRequestStatus,
                                                     int limit, int offset);

    List<RepairRequest> findAll();

    int countWithUser(int userId);

    int countWithStatus(RepairRequestStatus status);

    int countWithStatus(ReviewStatus reviewStatus, RepairRequestStatus repairRequestStatus);

    boolean updateReviewId(int RepairRequestId, int ReviewId);

    boolean updateStatus(int RepairRequestId, RepairRequestStatus repairRequestStatus);

    boolean update(RepairRequest repairRequest);

    boolean delete(int id);
}
