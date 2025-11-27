package org.example.easymart.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.easymart.entity.Commande;
import org.example.easymart.enumeration.PaymentStatus;
import org.example.easymart.enumeration.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaiementDtoResponse {

    private Long id;
    private CommandeDtoResponse commandeDtoResponse;
    private Integer numero_paiement;
    private BigDecimal montant;
    private PaymentType paymentType;
    private Date datePaiement;
    private PaymentStatus paymentStatus;
    private Date date_encaissement;
}
