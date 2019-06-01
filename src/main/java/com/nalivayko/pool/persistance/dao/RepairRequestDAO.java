package com.nalivayko.pool.persistance.dao;

import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;

import java.util.List;

public interface RepairRequestDAO {

    public int create(RepairRequest repairRequest);

    public List<RepairRequest> findByUserId(int UserId, int limit, int offset);

    public List<RepairRequest> findByRepairRequestStatus(RepairRequestStatus status);

    public List<RepairRequest> findByReviewAndRequestStatus(ReviewStatus reviewStatus,
                                                            RepairRequestStatus repairRequestStatus);

    public List<RepairRequest> findAll();

    public int countForUser(int userId);

    public boolean updateReviewId(int RepairRequestId, int ReviewId);

    public boolean updateStatus(int RepairRequestId, RepairRequestStatus repairRequestStatus);

    public boolean update(RepairRequest repairRequest);

    public boolean delete(int id);
}
