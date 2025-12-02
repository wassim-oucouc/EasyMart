package org.example.easymart.service;

import org.example.easymart.dto.request.ProduitDTO;
import org.example.easymart.dto.response.ProduitDtoResponse;

import java.util.List;

public interface ProduitService {

    public ProduitDtoResponse createProduit(ProduitDTO produitDTO);
    public ProduitDtoResponse updateProduit(Long id,ProduitDTO produitDTO);
    public List<ProduitDtoResponse> getallProduit();
    public void deleteProduitById(Long id);
    public String checkProduitsQuantity(List<Long> produitIds,Integer quantity);

}
