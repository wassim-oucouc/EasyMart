package org.example.easymart.dto.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.easymart.entity.Client;
import org.example.easymart.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class CommandeDTO {
    private Long id;
    private Long clientId;
    private List<ProduitDTO> produitDTOS;
    private Date date;
    private BigDecimal sous_total;
    private BigDecimal remise;
    private BigDecimal tva;
    private Long total;
    private String code_promo;
    private OrderStatus orderStatus;
}
