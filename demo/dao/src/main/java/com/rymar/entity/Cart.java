package com.rymar.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> list;

    private boolean processed;
}
