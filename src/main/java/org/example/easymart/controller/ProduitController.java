package org.example.easymart.controller;


import jakarta.validation.Valid;
import org.example.easymart.dto.request.ProduitDTO;
import org.example.easymart.dto.response.ProduitDtoResponse;
import org.example.easymart.service.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping("/product/create")
    public ResponseEntity<ProduitDtoResponse> createProduit(@Valid @RequestBody ProduitDTO produitDTO)
    {
        return ResponseEntity.ok().body(this.produitService.createProduit(produitDTO));
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<ProduitDtoResponse> updateProduit(@PathVariable("id") Long id,@Valid @RequestBody ProduitDTO produitDTO)
    {
        return ResponseEntity.ok().body(this.produitService.updateProduit(id,produitDTO));
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable Long id)
    {
        this.produitService.deleteProduitById(id);
        return ResponseEntity.ok().body("product is deleted");
    }

    @GetMapping("product/all/{size}/{page}")
    public ResponseEntity<Page<ProduitDtoResponse>> getAllProduitPaginate(@PathVariable("size") int size, @PathVariable("page") int page)
    {
        return ResponseEntity.ok().body(this.produitService.findProduitPaginated(page,size));
    }
}
