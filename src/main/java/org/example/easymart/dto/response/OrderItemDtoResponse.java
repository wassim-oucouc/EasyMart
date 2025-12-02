package org.example.easymart.dto.response;


import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrderItemDtoResponse {
    private Long id;
    private ProduitDtoResponse produitDtoResponse;
    private Integer quantite;
    private BigDecimal totalLigne;
}
