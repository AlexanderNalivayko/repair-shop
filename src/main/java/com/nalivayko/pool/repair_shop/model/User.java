package com.nalivayko.pool.repair_shop.model;

import com.nalivayko.pool.repair_shop.model.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole role;

    private String username;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    RepairRequest repairRequest;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Feedback> feedback;
}
