package org.example.easymart.controller;


import org.example.easymart.dto.request.ProduitDTO;
import org.example.easymart.dto.response.ProduitDtoResponse;
import org.example.easymart.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitController {

    private ProduitService produitService;

    public ProduitController(ProduitService produitService)
    {
        this.produitService = produitService;
    }

    @PostMapping("/product/create")
    public ResponseEntity<ProduitDtoResponse> createProduit(@RequestBody ProduitDTO produitDTO)
    {
        return ResponseEntity.ok().body(this.produitService.createProduit(produitDTO));
    }
}
