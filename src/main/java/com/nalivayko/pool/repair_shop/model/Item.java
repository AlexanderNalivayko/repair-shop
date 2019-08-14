package com.nalivayko.pool.repair_shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String itemType;
    private String brand;
    private String name;

    @OneToOne(mappedBy = "item")
    RepairRequest repairRequest;
}
