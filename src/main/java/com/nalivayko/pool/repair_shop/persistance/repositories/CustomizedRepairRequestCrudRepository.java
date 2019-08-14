package com.nalivayko.pool.repair_shop.persistance.repositories;

import com.nalivayko.pool.repair_shop.model.RepairRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomizedRepairRequestCrudRepository  extends PagingAndSortingRepository<RepairRequest, Integer> {

    List<RepairRequest> findAllById(Integer id, Pageable pageable);
}
