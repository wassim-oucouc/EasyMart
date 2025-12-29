package org.example.easymart.mapper;


import org.example.easymart.dto.request.OrderItemDTO;
import org.example.easymart.dto.response.OrderItemDtoResponse;
import org.example.easymart.entity.OrderItem;
import org.example.easymart.entity.Produit;
import org.example.easymart.repository.ProduitRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class  OrderItemMapper {
    @Autowired
    public ProduitRepository produitRepository;



    @Mapping(source = "produitId", target = "produit",qualifiedByName = "MapProduit")
    abstract OrderItem toEntity(OrderItemDTO dto);

    @Mapping(source = "produit", target = "produitDtoResponse")
    abstract OrderItemDtoResponse toDtoResponse(OrderItem entity);

    @Named("MapProduit")
    public  Produit mapProduitIdToProduit(Long produitId) {
        if (produitId == null) return null;
        Optional<Produit> produit = produitRepository.findById(produitId);
        return produit.orElse(null);
    }
}

