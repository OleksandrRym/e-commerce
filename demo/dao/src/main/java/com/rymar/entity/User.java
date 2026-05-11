package com.rymar.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID uuid;

    @Column(unique = true)
    private String username;

    @Column(name = "tg_id")
    private long tgId;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}