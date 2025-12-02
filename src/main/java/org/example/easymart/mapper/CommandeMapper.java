package org.example.easymart.mapper;


import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.response.CommandeDtoResponse;
import org.example.easymart.entity.Commande;
import org.example.easymart.entity.Produit;
import org.example.easymart.repository.ProduitRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, ProduitMapper.class})
public interface  CommandeMapper {

    @Mapping(source = "clientId", target = "client.id")
     public Commande toEntity(CommandeDTO dto);

    @Mapping(source = "client.id", target = "clientDtoResponse.id")
     public CommandeDtoResponse toDtoResponse(Commande entity);

}

