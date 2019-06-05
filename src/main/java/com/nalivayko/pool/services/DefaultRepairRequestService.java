package com.nalivayko.pool.services;

import com.nalivayko.pool.model.Item;
import com.nalivayko.pool.model.RepairRequest;
import com.nalivayko.pool.model.Review;
import com.nalivayko.pool.model.User;
import com.nalivayko.pool.model.enums.RepairRequestStatus;
import com.nalivayko.pool.model.enums.ReviewStatus;
import com.nalivayko.pool.persistance.TransactionManager;
import com.nalivayko.pool.persistance.dao.ItemDAO;
import com.nalivayko.pool.persistance.dao.RepairRequestDAO;
import com.nalivayko.pool.persistance.dao.ReviewDAO;

import java.util.List;

/**
 * Provides methods to work with RepairRequests (create, find, delete ...)
 */
public class DefaultRepairRequestService implements RepairRequestService {
    private static final int FRACTIONAL = 100;
    private ReviewDAO reviewDAO;
    private RepairRequestDAO repairRequestDAO;
    private ItemDAO itemDAO;
    private TransactionManager transactionManager;

    public DefaultRepairRequestService(ReviewDAO reviewDAO, RepairRequestDAO repairRequestDAO, ItemDAO itemDAO,
                                       TransactionManager transactionManager) {
        this.reviewDAO = reviewDAO;
        this.repairRequestDAO = repairRequestDAO;
        this.itemDAO = itemDAO;
        this.transactionManager = transactionManager;
    }

    /**
     * @param userId - id of user whom repair requests you want to get
     * @return all RepairRequests that was created by user with userId
     */
    @Override
    public List<RepairRequest> getAllByUserId(int userId, int limit, int offset) {
        transactionManager.getConnection();
        try {
            return repairRequestDAO.findByUserId(userId, limit, offset);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Create RepairRequest and save it
     *
     * @param user        - user that create repair request
     * @param itemType    - type of repair request item
     * @param itemBrand   - brand of item
     * @param itemName    name of item
     * @param description - description of malfunction
     */
    @Override
    public void createRepairRequest(User user, String itemType, String itemBrand, String itemName, String description) {
        try {
            transactionManager.startTransaction();
            Item item = new Item(itemType, itemBrand, itemName);
            int itemId = itemDAO.create(item);
            item.setId(itemId);
            RepairRequest repairRequest = new RepairRequest(user, item, RepairRequestStatus.NEW, description);
            repairRequestDAO.create(repairRequest);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * Find and return list of repair request where status equal {@code repairRequestStatus}
     *
     * @param repairRequestStatus - status which will be used for searching
     * @return List of repair requests
     */
    @Override
    public List<RepairRequest> getAllWithStatus(RepairRequestStatus repairRequestStatus, int limit, int offset) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.findByRepairRequestStatus(repairRequestStatus, limit, offset);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Find all repair requests with reviewStatus and repairRequestStatus
     * as passed in parameters.
     * @param reviewStatus all results will be with.
     * @param repairRequestStatus all results will be with.
     * @param limit amount of results subsequence (used for pagination)
     * @param offset start position of result subsequence (used for pagination)
     * @return RepairRequest
     */
    @Override
    public List<RepairRequest> getAllByReviewAndRequestStatus(ReviewStatus reviewStatus,
                                                              RepairRequestStatus repairRequestStatus,
                                                              int limit, int offset) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.findByReviewAndRequestStatus(reviewStatus,
                    repairRequestStatus, limit, offset);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Create ACCEPTED review and add it's id to repairRequest
     * @param repairRequestId of repairRequest you want to review
     * @param cost of repairRequest
     */
    @Override
    public void acceptRepairRequest(int repairRequestId, Integer cost) {
        try {
            transactionManager.startTransaction();
            int reviewId = reviewDAO.create(new Review(ReviewStatus.ACCEPTED, cost * FRACTIONAL));
            repairRequestDAO.updateReviewId(repairRequestId, reviewId);
            repairRequestDAO.updateStatus(repairRequestId, RepairRequestStatus.REVIEWED);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * Create REJECTED review and add it's id to repairRequest
     * @param repairRequestId of repairRequest you want to review
     * @param reason reason why repairRequest is REJECTED
     */
    @Override
    public void rejectRepairRequest(int repairRequestId, String reason) {
        try {
            transactionManager.startTransaction();
            int reviewId = reviewDAO.create(new Review(ReviewStatus.REJECTED, reason));
            repairRequestDAO.updateReviewId(repairRequestId, reviewId);
            repairRequestDAO.updateStatus(repairRequestId, RepairRequestStatus.REVIEWED);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * Update RepairRequests status
     */
    @Override
    public void updateStatus(int repairRequestId, RepairRequestStatus repairRequestStatus) {
        try {
            transactionManager.getConnection();
            repairRequestDAO.updateStatus(repairRequestId, repairRequestStatus);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Count number of RepairRequest with userId
     * @return number of RepairRequests
     */
    @Override
    public int countRequestsWithUserId(int userId) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.countWithUser(userId);
        } finally {
            transactionManager.closeConnection();

        }
    }

    /**
     * Count number of RepairRequest with status
     * @return number of RepairRequests
     */
    @Override
    public int countRequestsWithStatus(RepairRequestStatus status) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.countWithStatus(status);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Count number of RepairRequest with reviewStatus and repairRequestStatus
     * @return number of RepairRequests
     */
    @Override
    public int countRequestsWithStatus(ReviewStatus reviewStatus, RepairRequestStatus repairRequestStatus) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.countWithStatus(reviewStatus, repairRequestStatus);
        } finally {
            transactionManager.closeConnection();
        }
    }
}
