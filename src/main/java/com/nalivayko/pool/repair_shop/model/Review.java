package com.nalivayko.pool.repair_shop.model;

import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repair_requests")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private ReviewStatus status;
    private String time;
    private BigDecimal cost;
    private String rejectReason;

    @OneToOne(mappedBy = "review")
    RepairRequest repairRequest;
}
