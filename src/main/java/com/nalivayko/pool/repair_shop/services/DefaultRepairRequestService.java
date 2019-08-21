package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Item;
import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;
import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedItemCrudRepository;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedRepairRequestCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DefaultRepairRequestService implements RepairRequestService {

    @Autowired
    private CustomizedRepairRequestCrudRepository repairRequestRepo;
    @Autowired
    private CustomizedItemCrudRepository itemRepo;

    @Override
    public Page<RepairRequest> getAllByUserName(String username, Pageable pageable) {
        return repairRequestRepo.findAllByUsername(username, pageable);
    }

    @Override
    public void createRepairRequest(User user, String itemType, String itemBrand, String itemName, String description) {
        Item item = itemRepo.save(Item.builder()
                .itemType(itemType)
                .brand(itemBrand)
                .name(itemName)
                .build());
        repairRequestRepo.save(RepairRequest.builder()
                .user(user)
                .item(item)
                .description(description)
                .build());
    }

    @Override
    public Page<RepairRequest> getAllNew(Pageable pageable) {
        return repairRequestRepo.findAllByStatus(RepairRequestStatus.NEW, pageable);
    }

    @Override
    public Page<RepairRequest> getAllAccepted(Pageable pageable) {
        return repairRequestRepo.findByReviewStatusAndRepairRequestStatus(ReviewStatus.ACCEPTED, RepairRequestStatus.REVIEWED, pageable);
    }

    @Override
    public void acceptRepairRequest(String repairRequestId, String cost) {

//        try {
//            int repairId = Integer.parseInt(repairRequestId);
//            transactionManager.startTransaction();
//            int reviewId = reviewDAO.create(new Review(ReviewStatus.ACCEPTED, new BigDecimal(cost)));
//            repairRequestDAO.updateReviewId(repairId, reviewId);
//            repairRequestDAO.updateStatus(repairId, RepairRequestStatus.REVIEWED);
//        } finally {
//            transactionManager.endTransaction();
//        }
    }

    @Override
    public void rejectRepairRequest(int repairRequestId, String reason) {
//        try {
//            transactionManager.startTransaction();
//            int reviewId = reviewDAO.create(new Review(ReviewStatus.REJECTED, reason));
//            repairRequestDAO.updateReviewId(repairRequestId, reviewId);
//            repairRequestDAO.updateStatus(repairRequestId, RepairRequestStatus.REVIEWED);
//        } finally {
//            transactionManager.endTransaction();
//        }
    }

    @Override
    public void performRepairRequest(int repairRequestId) {
//        try {
//            transactionManager.getConnection();
//            repairRequestDAO.updateStatus(repairRequestId, RepairRequestStatus.DONE);
//        } finally {
//            transactionManager.closeConnection();
//        }
    }

    @Override
    public int countByUserId(int userId) {
//        try {
//            transactionManager.getConnection();
//            return repairRequestDAO.countWithUser(userId);
//        } finally {
//            transactionManager.closeConnection();
//        }
        return 0;
    }

    @Override
    public int countNew() {
//        try {
//            transactionManager.getConnection();
//            return repairRequestDAO.countWithStatus(RepairRequestStatus.NEW);
//        } finally {
//            transactionManager.closeConnection();
//        }
        return 0;
    }

    @Override
    public int countAccepted() {
//        try {
//            transactionManager.getConnection();
//            return repairRequestDAO.countWithStatus(ReviewStatus.ACCEPTED, RepairRequestStatus.REVIEWED);
//        } finally {
//            transactionManager.closeConnection();
//        }
        return 0;
    }
}
