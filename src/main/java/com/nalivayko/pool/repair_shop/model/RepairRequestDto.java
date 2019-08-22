package com.nalivayko.pool.repair_shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RepairRequestDto {
    private String type;
    private String brand;
    private String name;
    private String description;
}
