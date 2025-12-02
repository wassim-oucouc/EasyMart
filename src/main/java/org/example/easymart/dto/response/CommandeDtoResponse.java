package org.example.easymart.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.easymart.entity.Client;
import org.example.easymart.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CommandeDtoResponse {

    private Long id;
    private ClientDtoResponse clientDtoResponse;
    private List<ProduitDtoResponse> productDtoResponseList;
    private Date date;
    private Integer quantity;
    private BigDecimal sous_total;
    private BigDecimal remise;
    private BigDecimal tva;
    private BigDecimal total;
    private String code_promo;
    private BigDecimal montant_restant;
    private OrderStatus orderStatus;
}
