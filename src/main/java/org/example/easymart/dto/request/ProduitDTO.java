package org.example.easymart.dto.request;


import lombok.Data;

@Data
public class ProduitDTO {
    private Long id;
    private String nom;
    private Integer prix;
    private Long stock;
    private Boolean disabled;
}
