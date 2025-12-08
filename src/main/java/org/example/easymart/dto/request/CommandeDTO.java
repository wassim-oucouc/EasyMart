package org.example.easymart.dto.request;

import lombok.Data;
import org.example.easymart.enumeration.OrderStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CommandeDTO {

    private Long id;

    @NotNull(message = "Le clientId est obligatoire")
    private Long clientId;

    @NotEmpty(message = "La commande doit contenir au moins un article")
    private List<OrderItemDTO> orderItemDTOList;

    @NotNull(message = "La date de la commande est obligatoire")
    private Date date;

    @NotNull(message = "La quantité totale est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins 1")
    private Integer quantity;


    private BigDecimal sous_total;

    @PositiveOrZero(message = "La remise doit être positive ou nulle")
    private BigDecimal remise = BigDecimal.ZERO;


    private BigDecimal tva;


    private BigDecimal total;

    @Size(max = 50, message = "Le code promo ne doit pas dépasser 50 caractères")
    private String code_promo;


    private BigDecimal montant_restant;

    private OrderStatus orderStatus;
}
