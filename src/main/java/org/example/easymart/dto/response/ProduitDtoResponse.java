package org.example.easymart.dto.response;


import lombok.Data;

@Data
public class ProduitDtoResponse {
    private Long id;
    private String nom;
    private Integer prix;
    private Long stock;
}
