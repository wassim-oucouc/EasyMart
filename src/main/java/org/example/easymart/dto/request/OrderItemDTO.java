package org.example.easymart.dto.request;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class OrderItemDTO {

    private Long id;

    @NotNull(message = "Le produitId est obligatoire")
    private Long produitId;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins 1")
    private Integer quantite;

    @NotNull(message = "Le total de la ligne est obligatoire")
    @PositiveOrZero(message = "Le total de la ligne doit être positif ou nul")
    private BigDecimal totalLigne;
}
