package org.example.easymart.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.example.easymart.enumeration.CustomerTier;


@Entity
@Data
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private CustomerTier customerTier;


}
