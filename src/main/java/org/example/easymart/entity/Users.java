package org.example.easymart.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.easymart.enumeration.Role;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;


}
