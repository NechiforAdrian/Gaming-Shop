package com.gaming.shop.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_brand")
    private String productBrand;
    @Column(name = "colour")
    private String colour;
    @Column(name = "price")
    private double price;
    @ManyToMany(mappedBy = "products")
    private Set<Customer> customers;
}
