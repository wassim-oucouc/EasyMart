package org.example.easymart.controller;

import org.example.easymart.dto.request.PaiementDTO;
import org.example.easymart.dto.response.PaiementDtoResponse;
import org.example.easymart.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaiementController {

    private PaiementService paiementService;


    @Autowired
    public PaiementController(PaiementService paiementService)
    {
        this.paiementService = paiementService;
    }

    @PostMapping("/make/paiement")
    public ResponseEntity<PaiementDtoResponse> makePaiement(@RequestBody PaiementDTO paiementDTO)
    {
        return ResponseEntity.ok().body(this.paiementService.makePaiement(paiementDTO));
    }
}
