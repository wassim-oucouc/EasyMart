package org.example.easymart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
@Builder
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Integer prix;
    private Long stock;
    private Boolean disabled;

    public Produit() {

    }
}
