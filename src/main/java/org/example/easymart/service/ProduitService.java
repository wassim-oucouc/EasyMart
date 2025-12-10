package org.example.easymart.service;

import org.example.easymart.dto.request.ProduitDTO;
import org.example.easymart.dto.response.ProduitDtoResponse;
import org.example.easymart.entity.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitService {

    public ProduitDtoResponse createProduit(ProduitDTO produitDTO);
    public ProduitDtoResponse updateProduit(Long id,ProduitDTO produitDTO);
    public List<ProduitDtoResponse> getallProduit();
    public void deleteProduitById(Long id);
    public Page<ProduitDtoResponse> findProduitPaginated(int pageNumber, int pageSize);
    public ProduitDtoResponse findProduitByName(String name);
    public List<Produit> getProductsOrded();

}
