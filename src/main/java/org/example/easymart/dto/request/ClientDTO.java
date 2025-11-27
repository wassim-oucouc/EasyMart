package org.example.easymart.dto.request;


import lombok.Data;
import org.example.easymart.enumeration.CustomerTier;

@Data
public class ClientDTO {

    private Long id;
    private String nom;
    private String email;
    private Long userId;
    private CustomerTier customerTier;
}
