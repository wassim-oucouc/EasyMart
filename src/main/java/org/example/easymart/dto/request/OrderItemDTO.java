package org.example.easymart.dto.request;

import jakarta.persistence.*;
import lombok.Data;
import org.example.easymart.entity.Produit;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private Long produitId;
    private Integer quantite;
    private BigDecimal totalLigne;
}
