package com.nalivayko.pool.repair_shop.services;

import com.nalivayko.pool.repair_shop.model.*;
import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;
import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedItemCrudRepository;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedRepairRequestCrudRepository;
import com.nalivayko.pool.repair_shop.persistance.repositories.CustomizedReviewCrudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
@NoArgsConstructor
public class DefaultRepairRequestService implements RepairRequestService {
    @Autowired
    private CustomizedRepairRequestCrudRepository repairRequestRepo;
    @Autowired
    private CustomizedItemCrudRepository itemRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomizedReviewCrudRepository reviewRepo;

    @Override
    public Page<RepairRequest> getAllByUserName(String username, Pageable pageable) {
        return repairRequestRepo.findAllByUsername(username, pageable);
    }

    @Transactional
    @Override
    public RepairRequest createRepairRequest(RepairRequestDto repairRequestDto, String username) {
        Item item = itemRepo.save(
                Item.builder()
                        .name(repairRequestDto.getName())
                        .brand(repairRequestDto.getBrand())
                        .itemType(repairRequestDto.getType())
                        .build());
        User user = userService.getUserByUsername(username);
        return repairRequestRepo.save(RepairRequest.builder()
                .userId(user.getId())
                .itemId(item.getId())
                .user(user)
                .item(item)
                .description(repairRequestDto.getDescription())
                .creationTime(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .status(RepairRequestStatus.NEW)
                .build());
    }

    @Override
    public Page<RepairRequest> getAllNew(Pageable pageable) {
        return repairRequestRepo.findAllByStatus(RepairRequestStatus.NEW, pageable);
    }

    @Override
    public Page<RepairRequest> getAllAccepted(Pageable pageable) {
        return repairRequestRepo.findByReviewStatusAndRepairRequestStatus(ReviewStatus.ACCEPTED,
                RepairRequestStatus.REVIEWED, pageable);
    }

    @Transactional
    @Override
    public void acceptRepairRequest(Integer repairRequestId, Integer price) {
        Review review = reviewRepo.save(Review.builder()
                .cost(BigDecimal.valueOf(price))
                .status(ReviewStatus.ACCEPTED)
                .time(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build());
        repairRequestRepo.updateReviewById(repairRequestId, review.getId());
    }

    @Transactional
    @Override
    public void rejectRepairRequest(Integer repairRequestId, String reason) {
        Review review = reviewRepo.save(Review.builder()
                .rejectReason(reason)
                .status(ReviewStatus.REJECTED)
                .time(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build());
        repairRequestRepo.updateReviewById(repairRequestId, review.getId());
    }

    @Override
    public void performRepairRequest(Integer repairRequestId) {
//        try {
//            transactionManager.getConnection();
//            repairRequestDAO.updateStatus(repairRequestId, RepairRequestStatus.DONE);
//        } finally {
//            transactionManager.closeConnection();
//        }
    }
}
