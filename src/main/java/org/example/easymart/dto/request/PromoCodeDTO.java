package org.example.easymart.dto.request;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class PromoCodeDTO {
    private Long id;
    private String code;
    private BigDecimal pourcentage;
}
