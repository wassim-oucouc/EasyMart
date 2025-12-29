package org.example.easymart.dto.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import java.math.BigDecimal;

@Data
public class PromoCodeDTO {

    private Long id;

    @NotBlank(message = "Le code promo est obligatoire")
    private String code;

    @NotNull(message = "Le pourcentage est obligatoire")
    @DecimalMin(value = "0.0", message = "Le pourcentage doit être supérieur à 0")
    @DecimalMax(value = "100.0",  message = "Le pourcentage ne peut pas dépasser 100")
    private BigDecimal pourcentage;
}
