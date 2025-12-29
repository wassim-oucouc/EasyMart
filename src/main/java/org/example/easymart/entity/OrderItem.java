package org.example.easymart.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Produit produit;
    @Column(nullable = false)
    private Integer quantite;

    @Column(name = "total_ligne", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalLigne;
}
