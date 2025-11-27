package org.example.easymart.service.impl;
import org.example.easymart.dto.request.ProduitDTO;
import org.example.easymart.dto.response.ProduitDtoResponse;
import org.example.easymart.entity.Produit;
import org.example.easymart.exception.ProduitNotFoundException;
import org.example.easymart.mapper.ProduitMapper;
import org.example.easymart.repository.ProduitRepository;
import org.example.easymart.service.CommandeService;
import org.example.easymart.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;
    private final CommandeService commandeService;



    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMapper produitMapper, CommandeService commandeService)
    {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
        this.commandeService = commandeService;
    }

    public ProduitDtoResponse createProduit(ProduitDTO produitDTO)
    {
        Produit produit = this.produitMapper.toEntity(produitDTO);
         return this.produitMapper.toDtoResponse(this.produitRepository.save(produit));

    }
    public ProduitDtoResponse updateProduit(Long id,ProduitDTO produitDTO)
    {
        Produit produit =  this.produitRepository.findById(id).orElseThrow(() -> new ProduitNotFoundException("product not exits with id : " + id));
        Produit produitUpdated = Produit.builder().id(id).nom(produitDTO.getNom()).prix(produitDTO.getPrix()).stock(produitDTO.getStock()).disabled(produitDTO.getDisabled()).build();
        return this.produitMapper.toDtoResponse(this.produitRepository.save(produitUpdated));
    }

    public List<ProduitDtoResponse> getallProduit()
    {
        return this.produitRepository.findAll().stream().map(produitMapper::toDtoResponse).toList();
    }
    public void deleteProduitById(Long id)
    {
       Produit produit =  this.produitRepository.findById(id).orElseThrow(() -> new ProduitNotFoundException("produit not exists id : " + id));
        Boolean check = this.commandeService.checkCommandeExitsByProductId(id);
        if (check)
        {
            produit.setDisabled(true);
            this.produitRepository.save(produit);
        }
        else
        {
            this.produitRepository.delete(produit);
        }
    }
}
