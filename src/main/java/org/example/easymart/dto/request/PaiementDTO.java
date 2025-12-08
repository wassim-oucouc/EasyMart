package org.example.easymart.dto.request;

import lombok.Data;
import org.example.easymart.enumeration.PaymentStatus;
import org.example.easymart.enumeration.PaymentType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaiementDTO {

    private Long id;

    @NotNull(message = "Le commandeId est obligatoire")
    private Long commandeId;

    @NotNull(message = "Le numéro de paiement est obligatoire")
    @Min(value = 1, message = "Le numéro de paiement doit être au moins 1")
    private Integer numero_paiement;

    @NotNull(message = "Le montant est obligatoire")
    @PositiveOrZero(message = "Le montant doit être positif ou nul")
    private BigDecimal montant;

    @Size(max = 100, message = "Le nom de la banque ne doit pas dépasser 100 caractères")
    private String bankName;

    @NotNull(message = "Le type de paiement est obligatoire")
    private PaymentType paymentType;

    @NotNull(message = "La date de paiement est obligatoire")
    private Date datePaiement;

    @NotNull(message = "Le statut de paiement est obligatoire")
    private PaymentStatus paymentStatus;

    private Date date_encaissement;
}
