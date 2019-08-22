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
    public RepairRequest createRepairRequest(RepairRequest repairRequest) {
        Item itemToBeSaved = repairRequest.getItem();
        Item item = itemRepo.save(Item.builder()
                .itemType(itemToBeSaved.getItemType())
                .brand(itemToBeSaved.getBrand())
                .name(itemToBeSaved.getName())
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
}
