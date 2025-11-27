package org.example.easymart.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.easymart.entity.User;
import org.example.easymart.enumeration.CustomerTier;

@Data
public class ClientDtoResponse {
    private Long id;
    private String nom;
    private String email;
    private UserDtoResponse userDtoResponse;
    private CustomerTier customerTier;
}
