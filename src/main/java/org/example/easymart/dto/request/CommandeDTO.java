package org.example.easymart.dto.request;

import lombok.Data;
import org.example.easymart.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class CommandeDTO {
    private Long id;
    private Long clientId;
    private List<OrderItemDTO> orderItemDTOList;
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
