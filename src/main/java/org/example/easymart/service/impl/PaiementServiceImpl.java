package org.example.easymart.service.impl;

import jakarta.transaction.Transactional;
import org.example.easymart.dto.request.PaiementDTO;
import org.example.easymart.dto.response.CommandeDtoResponse;
import org.example.easymart.dto.response.PaiementDtoResponse;
import org.example.easymart.entity.Paiement;
import org.example.easymart.enumeration.PaymentStatus;
import org.example.easymart.exception.IllegalPaymentTypeException;
import org.example.easymart.mapper.PaiementMapper;
import org.example.easymart.repository.PaiementRepository;
import org.example.easymart.service.CommandeService;
import org.example.easymart.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import static org.example.easymart.enumeration.PaymentType.ESPÈCES;

@Service
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final CommandeService commandeService;
    private final PaiementMapper paiementMapper;

    @Autowired
    public PaiementServiceImpl(PaiementRepository paiementRepository, CommandeService commandeService, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.commandeService = commandeService;
        this.paiementMapper = paiementMapper;
    }

    @Transactional
    public PaiementDtoResponse makePaiement(PaiementDTO paiementDTO)
    {
        CommandeDtoResponse commandeDtoResponse = this.commandeService.getCommandeById(paiementDTO.getCommandeId());
        if(commandeDtoResponse.getMontant_restant().equals(BigDecimal.ZERO))
        {
            throw new IllegalStateException("Payment Already Full Paid No Payment Needs!");
        }
        if(paiementDTO.getMontant().compareTo(BigDecimal.valueOf(20000)) > 0 && paiementDTO.getPaymentType().equals(ESPÈCES)) {
            throw new IllegalPaymentTypeException("Payment Type Illegal Please Choose a legal Type 20K LIMIT!");
        }
        if(paiementDTO.getMontant().compareTo(commandeDtoResponse.getMontant_restant()) > 0)
        {
            throw new IllegalStateException("The Amount Trying to Pay is More Than The Balance To Pay");
        }

        if(!paiementRepository.getPaiementsByCommande_Id(paiementDTO.getCommandeId()).isPresent()) {
            paiementDTO.setNumero_paiement(1);
        }
        else
        {
            Paiement paiement = paiementRepository.getPaiementsByCommande_Id(paiementDTO.getCommandeId()).get();
            if(paiement.getNumero_paiement() == null)
            {
                paiementDTO.setNumero_paiement(1);
            }
            else {
                paiementDTO.setNumero_paiement(paiement.getNumero_paiement() + 1);
            }
        }
        paiementDTO.setDatePaiement(new Date());
        paiementDTO.setDate_encaissement(paiementDTO.getDatePaiement());
        paiementDTO.setPaymentStatus(PaymentStatus.ENCAISSÉ);
        this.commandeService.DeductMotantFromMotantRestant(paiementDTO.getMontant(), paiementDTO.getCommandeId());
        Paiement paiement = this.paiementMapper.toEntity(paiementDTO);
      return  this.paiementMapper.toDtoResponse(this.paiementRepository.save(paiement));


    }
}
