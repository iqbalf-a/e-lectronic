package com.iqbalfa.electronic.model;

import lombok.Data;
import lombok.ToString;


import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "m_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name")
    private String productName;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer price;
}