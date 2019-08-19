package com.nalivayko.pool.repair_shop.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "item_type")
    private String itemType;
    private String brand;
    @Column(name = "item_name")
    private String name;

    @OneToOne(mappedBy = "item")
    RepairRequest repairRequest;
}
