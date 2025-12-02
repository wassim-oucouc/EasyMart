package org.example.easymart.service.impl;

import org.example.easymart.dto.request.PaiementDTO;
import org.example.easymart.dto.response.CommandeDtoResponse;
import org.example.easymart.dto.response.PaiementDtoResponse;
import org.example.easymart.entity.Paiement;
import org.example.easymart.exception.IllegalPaymentType;
import org.example.easymart.mapper.PaiementMapper;
import org.example.easymart.repository.PaiementRepository;
import org.example.easymart.service.CommandeService;
import org.example.easymart.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.example.easymart.enumeration.PaymentType.ESPÈCES;

@Service
public class PaiementServiceImpl implements PaiementService {

    private PaiementRepository paiementRepository;
    private CommandeService commandeService;
    private PaiementMapper paiementMapper;

    @Autowired
    public PaiementServiceImpl(PaiementRepository paiementRepository, CommandeService commandeService, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.commandeService = commandeService;
        this.paiementMapper = paiementMapper;
    }

    public PaiementDtoResponse makePaiement(PaiementDTO paiementDTO)
    {
        CommandeDtoResponse commandeDtoResponse = this.commandeService.getCommandeById(paiementDTO.getCommandeId());
        if(commandeDtoResponse.getMontant_restant().equals(BigDecimal.ZERO))
        {
            throw new IllegalStateException("Payment Already Full Paid No Payment Needs!");
        }
        if(paiementDTO.getMontant().compareTo(BigDecimal.valueOf(20000)) > 0 && paiementDTO.getPaymentType().equals(ESPÈCES)) {
            throw new IllegalPaymentType("Payment Type Illegal Please Choose a legal Type 20K LIMIT!");
        }
        if(paiementDTO.getMontant().compareTo(commandeDtoResponse.getMontant_restant()) > 0)
        {
            throw new IllegalStateException("The Amount Trying to Pay is More Than The Balance To Pay");
        }

        this.commandeService.DeductMotantFromMotantRestant(paiementDTO.getMontant(),paiementDTO.getCommandeId());

        Paiement paiement = this.paiementMapper.toEntity(paiementDTO);
      return  this.paiementMapper.toDtoResponse(this.paiementRepository.save(paiement));


    }
}
