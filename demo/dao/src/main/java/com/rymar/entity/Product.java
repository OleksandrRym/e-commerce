package com.rymar.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String name;
    public BigDecimal price;
    public int count;
}