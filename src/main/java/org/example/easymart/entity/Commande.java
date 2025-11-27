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
    @ManyToMany
    @JoinTable(
            name = "commande_products",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Produit> productList;
    private Date date;
    private BigDecimal sous_total;
    private BigDecimal remise;
    private BigDecimal tva;
    private Long total;
    private String code_promo;
    private OrderStatus orderStatus;




}
