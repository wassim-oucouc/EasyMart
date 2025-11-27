package org.example.easymart.mapper;


import org.example.easymart.dto.request.ProduitDTO;
import org.example.easymart.dto.response.ProduitDtoResponse;
import org.example.easymart.entity.Produit;
import org.mapstruct.Mapper;

@Mapper(   componentModel = "spring")
public interface ProduitMapper{
    public  Produit toEntity(ProduitDTO productDTO);
    public ProduitDtoResponse toDtoResponse(Produit product);
}
