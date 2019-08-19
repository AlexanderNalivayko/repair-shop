package com.nalivayko.pool.repair_shop.persistance.repositories;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizedRepairRequestCrudRepository  extends PagingAndSortingRepository<RepairRequest, Integer> {

    List<RepairRequest> findAllById(Integer id, Pageable pageable);
    List<RepairRequest> findAllByStatus(String status, Pageable pageable);

//    //TODO pagination works ?
//    @Query("SELECT repair_requests.id, users.role, repair_requests.user_id, users.username, users.first_name, users.last_name, users.email, users.phone, repair_requests.item_id, items.item_type, items.brand, items.item_name, repair_requests.review_id, reviews.review_status, reviews.review_time, reviews.cost, reviews.reject_reason, repair_requests.status, repair_requests.creation_time, repair_requests.description " +
//            "FROM repair_requests " +
//            "LEFT JOIN users ON users.id = repair_requests.user_id " +
//            "LEFT JOIN items ON items.id = repair_requests.item_id " +
//            "LEFT JOIN reviews ON reviews.id = repair_requests.review_id " +
//            "WHERE review_status LIKE ?1  AND status LIKE ?1 ORDER BY UNIX_TIMESTAMP(creation_time) DESC")
//    List<RepairRequest> findByReviewStatusAndRepairRequestStatus(String reviewStatus,
//                                                                 String repairRequestStatus,
//                                                                 Pageable pageable);
}
