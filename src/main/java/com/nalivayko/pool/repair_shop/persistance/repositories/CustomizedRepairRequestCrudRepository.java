package com.nalivayko.pool.repair_shop.persistance.repositories;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedRepairRequestCrudRepository extends PagingAndSortingRepository<RepairRequest, Integer> {

    @Query(value = "SELECT repair_requests.id, users.role, repair_requests.user_id, users.username, users.first_name, users.last_name, users.email, users.phone, repair_requests.item_id, items.item_type, items.brand, items.item_name, repair_requests.review_id, reviews.review_status, reviews.review_time, reviews.cost, reviews.reject_reason, repair_requests.status, repair_requests.creation_time, repair_requests.description " +
            "FROM repair_requests " +
            "LEFT JOIN users ON users.id = repair_requests.user_id " +
            "LEFT JOIN items ON items.id = repair_requests.item_id " +
            "LEFT JOIN reviews ON reviews.id = repair_requests.review_id " +
            "WHERE users.username LIKE ?1 ORDER BY UNIX_TIMESTAMP(creation_time) DESC",
            countQuery = "SELECT COUNT(*) " +
                    "FROM repair_requests " +
                    "LEFT JOIN users ON users.id = repair_requests.user_id " +
                    "WHERE users.username LIKE ?1",
            nativeQuery = true)
    Page<RepairRequest> findAllByUsername(String username, Pageable pageable);

    Page<RepairRequest> findAllByStatus(RepairRequestStatus status, Pageable pageable);


    @Query(value = "SELECT repair_requests.id, users.role, repair_requests.user_id, users.username, users.first_name, users.last_name, users.email, users.phone, repair_requests.item_id, items.item_type, items.brand, items.item_name, repair_requests.review_id, reviews.review_status, reviews.review_time, reviews.cost, reviews.reject_reason, repair_requests.status, repair_requests.creation_time, repair_requests.description " +
            "FROM repair_requests " +
            "LEFT JOIN users ON users.id = repair_requests.user_id " +
            "LEFT JOIN items ON items.id = repair_requests.item_id " +
            "LEFT JOIN reviews ON reviews.id = repair_requests.review_id " +
            "WHERE reviews.review_status LIKE ?1  AND repair_requests.status LIKE ?2 ORDER BY UNIX_TIMESTAMP(repair_requests.creation_time) DESC",
            countQuery = "SELECT COUNT(*) " +
                    "FROM repair_requests " +
                    "LEFT JOIN reviews ON reviews.id = repair_requests.review_id " +
                    "WHERE reviews.review_status LIKE ?1  AND repair_requests.status LIKE ?2",
            nativeQuery = true)
    Page<RepairRequest> findByReviewStatusAndRepairRequestStatus(String reviewStatus,
                                                                 String repairRequestStatus,
                                                                 Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE repair_requests SET repair_requests.review_id = ?2, status = 'REVIEWED' WHERE repair_requests.id = ?1",
            nativeQuery = true)
    void updateReviewById(Integer repairRequestId, Integer reviewId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE repair_requests SET repair_requests.status = 'DONE' WHERE repair_requests.id = ?1",
            nativeQuery = true)
    void performRepariRequestById(Integer repairRequestId);
}
