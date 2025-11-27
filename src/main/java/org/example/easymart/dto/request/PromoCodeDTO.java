package org.example.easymart.dto.request;


import lombok.Data;

@Data
public class PromoCodeDTO {
    private Long id;
    private String code;
    private Long pourcentage;
}
