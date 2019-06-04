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

public class DefaultRepairRequestService implements RepairRequestService {

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
        List<RepairRequest> repairRequests = repairRequestDAO.findByUserId(userId, limit, offset);
        //todo what if findById will throw an exception ?
        transactionManager.closeConnection();
        return repairRequests;

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
        transactionManager.startTransaction();
        Item item = new Item(itemType, itemBrand, itemName);
        int itemId = itemDAO.create(item);
        item.setId(itemId);
        RepairRequest repairRequest = new RepairRequest(user, item, RepairRequestStatus.NEW, description);
        repairRequestDAO.create(repairRequest);
        transactionManager.endTransaction();
    }

    /**
     * Find and return list of repair request where status equal {@code repairRequestStatus}
     *
     * @param repairRequestStatus - status which will be used for searching
     * @return List of repair requests
     */
    @Override
    public List<RepairRequest> getAllWithStatus(RepairRequestStatus repairRequestStatus, int limit, int offset) {
        transactionManager.getConnection();
        List<RepairRequest> repairRequests =
                repairRequestDAO.findByRepairRequestStatus(repairRequestStatus, limit, offset);
        //todo what if findById will throw an exception ?
        transactionManager.closeConnection();
        return repairRequests;
    }

    @Override
    public List<RepairRequest> getAllByReviewAndRequestStatus(ReviewStatus reviewStatus,
                                                              RepairRequestStatus repairRequestStatus,
                                                              int limit, int offset) {
        transactionManager.getConnection();
        List<RepairRequest> repairRequests = repairRequestDAO.findByReviewAndRequestStatus(reviewStatus,
                repairRequestStatus, limit, offset);
        //todo what if findById will throw an exception ?
        transactionManager.closeConnection();
        return repairRequests;
    }

    @Override
    public void updateReview(int repairRequestId, ReviewStatus reviewStatus, Integer cost) {
        transactionManager.startTransaction();
        int reviewId = reviewDAO.create(new Review(reviewStatus, cost));
        repairRequestDAO.updateReviewId(repairRequestId, reviewId);
        repairRequestDAO.updateStatus(repairRequestId, RepairRequestStatus.REVIEWED);
        transactionManager.endTransaction();
    }

    @Override
    public void updateStatus(int repairRequestId, RepairRequestStatus repairRequestStatus) {
        transactionManager.getConnection();
        repairRequestDAO.updateStatus(repairRequestId, repairRequestStatus);
        transactionManager.closeConnection();
    }

    @Override
    public int countRequestsWithUserId(int userId) {
        transactionManager.getConnection();
        int count = repairRequestDAO.countWithUser(userId);
        transactionManager.closeConnection();
        return count;
    }

    @Override
    public int countRequestsWithStatus(RepairRequestStatus status) {
        transactionManager.getConnection();
        int count = repairRequestDAO.countWithStatus(status);
        transactionManager.closeConnection();
        return count;
    }

    @Override
    public int countRequestsWithStatus(ReviewStatus reviewStatus, RepairRequestStatus repairRequestStatus) {
        transactionManager.getConnection();
        int count = repairRequestDAO.countWithStatus(reviewStatus, repairRequestStatus);
        transactionManager.closeConnection();
        return count;
    }
}
