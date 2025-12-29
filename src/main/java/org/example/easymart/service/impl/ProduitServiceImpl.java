package org.example.easymart.service.impl;
import lombok.extern.slf4j.Slf4j;
import org.example.easymart.aop.annotations.Secured;
import org.example.easymart.dto.request.ProduitDTO;
import org.example.easymart.dto.response.ProduitDtoResponse;
import org.example.easymart.entity.OrderItem;
import org.example.easymart.entity.Produit;
import org.example.easymart.exception.InsufficientQuantityException;
import org.example.easymart.exception.ProduitNotFoundException;
import org.example.easymart.mapper.ProduitMapper;
import org.example.easymart.repository.OrderItemRepository;
import org.example.easymart.repository.ProduitRepository;
import org.example.easymart.service.CommandeService;
import org.example.easymart.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;
    private final CommandeService commandeService;
    private OrderItemRepository orderItemRepository;



    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMapper produitMapper, CommandeService commandeService,OrderItemRepository orderItemRepository)
    {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
        this.commandeService = commandeService;
        this.orderItemRepository = orderItemRepository;
    }

    @Secured(role = {"ADMIN"})
    public ProduitDtoResponse createProduit(ProduitDTO produitDTO)
    {
        Produit produit = this.produitMapper.toEntity(produitDTO);
         return this.produitMapper.toDtoResponse(this.produitRepository.save(produit));

    }
    @Secured(role = {"ADMIN"})
    public ProduitDtoResponse updateProduit(Long id,ProduitDTO produitDTO)
    {
        Produit produit =  this.produitRepository.findById(id).orElseThrow(() -> new ProduitNotFoundException("product not exits with id : " + id));
        Produit produitUpdated = Produit.builder().id(id).nom(produitDTO.getNom()).prix(produitDTO.getPrix()).stock(produitDTO.getStock()).disabled(produitDTO.getDisabled()).build();
        return this.produitMapper.toDtoResponse(this.produitRepository.save(produitUpdated));
    }
    @Secured(role = {"ADMIN","CLIENT"})
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
    @Secured(role = {"ADMIN","CLIENT"})
    public Page<ProduitDtoResponse> findProduitPaginated(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
       Page<Produit> produit =   this.produitRepository.findAll(pageable);
       return produit.map(produitMapper::toDtoResponse);
    }

    public List<Produit> getProductsOrded()
    {
        return this.orderItemRepository.findAll().stream().map(OrderItem::getProduit).filter(orderItem -> orderItem.getPrix() > 2000).toList();
    }
    @Secured(role = {"ADMIN","CLIENT"})
    public ProduitDtoResponse findProduitByName(String name)
    {
        Produit produit =  this.produitRepository.findByNom(name);

        return this.produitMapper.toDtoResponse(produit);
    }
}
