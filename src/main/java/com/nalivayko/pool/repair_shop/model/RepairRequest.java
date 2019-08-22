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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "item_id")
    private Integer itemId;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "items")
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "item_id", insertable = false, updatable = false)
    private Item item;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "review_id", insertable = false, updatable = false)
    private Review review;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RepairRequestStatus status;

    @Column(name = "creation_time")
    private String creationTime;

    private String description;
}
