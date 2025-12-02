package org.example.easymart.service;

import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.response.CommandeDtoResponse;

import java.math.BigDecimal;

public interface CommandeService {

    public Boolean checkCommandeExitsByProductId(Long id);
    public CommandeDtoResponse createCommande(CommandeDTO commandeDTO);
    public CommandeDtoResponse confirmCommande(Long id);
    public CommandeDtoResponse getCommandeById(Long commandeId);
    public void DeductMotantFromMotantRestant(BigDecimal montant, Long commandeId);

}
