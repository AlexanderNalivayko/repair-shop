package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.Item;
import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.User;
import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedItemCrudRepository;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedRepairRequestCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class DefaultRepairRequestService implements RepairRequestService {

    @Autowired
    private CustomizedRepairRequestCrudRepository repairRequestRepo;
    @Autowired
    private CustomizedItemCrudRepository itemRepo;

    @Override
    public List<RepairRequest> getAllByUserId(int userId, int limit, int offset) {
        return repairRequestRepo.findAllById(userId, PageRequest.of(offset, limit));
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
    public List<RepairRequest> getAllNew(int limit, int offset) {
        return repairRequestRepo.findAllByStatus(RepairRequestStatus.NEW.toString(), PageRequest.of(offset, limit));
    }

    @Override
    public List<RepairRequest> getAllAccepted(int limit, int offset) {
//        RepairRequestQuery.SELECT_BY_REVIEW_AND_REQUEST_STATUS;
//        try {
//            transactionManager.getConnection();
//            return repairRequestDAO.findByReviewAndRequestStatus(ReviewStatus.ACCEPTED,
//                    RepairRequestStatus.REVIEWED, limit, offset);
//        } finally {
//            transactionManager.closeConnection();
//        }
        return null;
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
