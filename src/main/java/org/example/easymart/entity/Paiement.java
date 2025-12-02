package org.example.easymart.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.example.easymart.enumeration.PaymentStatus;
import org.example.easymart.enumeration.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
    private Integer numero_paiement;
    private BigDecimal montant;
    private String bankName;
    private PaymentType paymentType;
    private Date datePaiement;
    private Date dueDate;
    private PaymentStatus paymentStatus;
    private Date date_encaissement;

}
