package org.example.easymart.service;


import org.example.easymart.dto.request.PaiementDTO;
import org.example.easymart.dto.response.PaiementDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface PaiementService {

    public PaiementDtoResponse makePaiement(PaiementDTO paiementDTO);
}
