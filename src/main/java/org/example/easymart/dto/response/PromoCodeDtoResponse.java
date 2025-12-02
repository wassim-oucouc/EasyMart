    package org.example.easymart.dto.response;

    import lombok.Data;

    import java.math.BigDecimal;

    @Data
    public class PromoCodeDtoResponse {
        private Long id;
        private String code;
        private BigDecimal pourcentage;
    }
