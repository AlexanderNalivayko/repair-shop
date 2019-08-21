package com.nalivayko.pool.repair_shop.model;

import com.nalivayko.pool.repair_shop.model.enums.RepairRequestStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "repair_requests")
public class RepairRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Item item;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Review review;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RepairRequestStatus status;

    @Column(name = "creation_time")
    private String creationTime;

    private String description;
}
