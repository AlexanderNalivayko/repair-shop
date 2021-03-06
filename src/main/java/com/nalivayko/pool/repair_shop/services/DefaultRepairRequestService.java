package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Item;
import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.Review;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;
import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;
import com.nalivayko.pool.repair_shop.persistance.TransactionManager;
import com.nalivayko.pool.repair_shop.persistance.dao.ItemDAO;
import com.nalivayko.pool.repair_shop.persistance.dao.RepairRequestDAO;
import com.nalivayko.pool.repair_shop.persistance.dao.ReviewDAO;

import java.math.BigDecimal;
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
     * Find and return list of repair request where status is NEW
     *
     * @return List of repair requests
     */
    @Override
    public List<RepairRequest> getAllNew(int limit, int offset) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.findByRepairRequestStatus(RepairRequestStatus.NEW, limit, offset);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Find all repair requests with reviewStatus and repairRequestStatus
     * as passed in parameters.
     *
     * @param limit  amount of results subsequence (used for pagination)
     * @param offset start position of result subsequence (used for pagination)
     * @return RepairRequest
     */
    @Override
    public List<RepairRequest> getAllAccepted(int limit, int offset) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.findByReviewAndRequestStatus(ReviewStatus.ACCEPTED,
                    RepairRequestStatus.REVIEWED, limit, offset);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Create ACCEPTED review and add it's id to repairRequest
     *
     * @param repairRequestId of repairRequest you want to review
     * @param cost            of repairRequest
     */
    @Override
    public void acceptRepairRequest(String repairRequestId, String cost) {
        try {
            int repairId = Integer.parseInt(repairRequestId);
            transactionManager.startTransaction();
            int reviewId = reviewDAO.create(new Review(ReviewStatus.ACCEPTED, new BigDecimal(cost)));
            repairRequestDAO.updateReviewId(repairId, reviewId);
            repairRequestDAO.updateStatus(repairId, RepairRequestStatus.REVIEWED);
        } finally {
            transactionManager.endTransaction();
        }
    }

    /**
     * Create REJECTED review and add it's id to repairRequest
     *
     * @param repairRequestId of repairRequest you want to review
     * @param reason          reason why repairRequest is REJECTED
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
    public void performRepairRequest(int repairRequestId) {
        try {
            transactionManager.getConnection();
            repairRequestDAO.updateStatus(repairRequestId, RepairRequestStatus.DONE);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Count number of RepairRequest with userId
     *
     * @return number of RepairRequests
     */
    @Override
    public int countByUserId(int userId) {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.countWithUser(userId);
        } finally {
            transactionManager.closeConnection();

        }
    }

    /**
     * Count number of RepairRequest with status
     *
     * @return number of RepairRequests
     */
    @Override
    public int countNew() {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.countWithStatus(RepairRequestStatus.NEW);
        } finally {
            transactionManager.closeConnection();
        }
    }

    /**
     * Count number of RepairRequest with reviewStatus and repairRequestStatus
     *
     * @return number of RepairRequests
     */
    @Override
    public int countAccepted() {
        try {
            transactionManager.getConnection();
            return repairRequestDAO.countWithStatus(ReviewStatus.ACCEPTED, RepairRequestStatus.REVIEWED);
        } finally {
            transactionManager.closeConnection();
        }
    }
}
