package org.example.easymart.mapper;


import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.response.CommandeDtoResponse;
import org.example.easymart.entity.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, ProduitMapper.class})
public interface CommandeMapper {

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "produitDTOS", target = "productList")
    Commande toEntity(CommandeDTO dto);

    @Mapping(source = "client.id", target = "clientDtoResponse.id")
    @Mapping(source = "productList", target = "productDtoResponseList")
    CommandeDtoResponse toDtoResponse(Commande entity);
}

