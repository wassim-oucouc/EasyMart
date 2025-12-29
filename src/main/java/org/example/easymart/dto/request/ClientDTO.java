package org.example.easymart.dto.request;

import lombok.Data;
import org.example.easymart.enumeration.CustomerTier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class ClientDTO {

    private Long id;

    @NotBlank(message = "Le nom du client est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    private Long userId;

    @NotNull(message = "Le niveau du client est obligatoire")
    private CustomerTier customerTier;
}
