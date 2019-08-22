package com.nalivayko.pool.repair_shop.model;

import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_status", length = 20)
    private ReviewStatus status;

    @Column(name = "review_time")
    private String time;

    private BigDecimal cost;

    @Column(name = "reject_reason")
    private String rejectReason;

    @OneToOne(mappedBy = "review", orphanRemoval = true)
    RepairRequest repairRequest;
}
