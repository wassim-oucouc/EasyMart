package org.example.easymart.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.example.easymart.enumeration.OrderStatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data

public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany
    private List<OrderItem> orderItems;
    private Date date;
    private BigDecimal sous_total;
    private BigDecimal remise;
    private Integer quantity;
    private BigDecimal tva;
    private BigDecimal total;
    private String code_promo;
    private BigDecimal montant_restant;
    private OrderStatus orderStatus;




}
