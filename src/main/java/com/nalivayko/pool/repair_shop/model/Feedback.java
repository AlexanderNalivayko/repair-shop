package com.nalivayko.pool.repair_shop.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String text;

    @Column(name = "creation_time")
    private String creationTime;
}

