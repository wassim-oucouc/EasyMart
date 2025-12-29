package org.example.easymart.mapper;


import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.request.OrderItemDTO;
import org.example.easymart.dto.response.CommandeDtoResponse;
import org.example.easymart.dto.response.OrderItemDtoResponse;
import org.example.easymart.entity.Client;
import org.example.easymart.entity.Commande;
import org.example.easymart.entity.OrderItem;
import org.example.easymart.entity.Produit;
import org.example.easymart.exception.ClientNotFoundException;
import org.example.easymart.repository.ClientRepository;
import org.example.easymart.repository.CommandeRepository;
import org.example.easymart.repository.ProduitRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, ProduitMapper.class})
public abstract class  CommandeMapper {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Mapping(source = "clientId", target = "client",qualifiedByName = "MapClient")
    @Mapping(source = "orderItemDTOList" , target = "orderItems",qualifiedByName = "MapOrderToEntity")
    public abstract Commande toEntity(CommandeDTO dto);

    @Mapping(source = "client", target = "clientDtoResponse")
    @Mapping(source = "orderItems" , target = "orderItemDtoResponseList",qualifiedByName = "MapOrderToDToResponse")
     public abstract CommandeDtoResponse toDtoResponse(Commande entity);

    @Named("MapClient")
    public Client mapToClient(Long clientId)
    {
        return this.clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("client not exists id" + clientId));

    }

    @Named("MapOrderToDToResponse")
    public List<OrderItemDtoResponse> mapOrderItemToDtoResponse(List<OrderItem> orderItem)
    {
        return orderItem.stream().map(orderItemMapper::toDtoResponse).toList();
    }

    @Named("MapOrderToEntity")
    public List<OrderItem> mapOrderItemToEntity(List<OrderItemDTO> orderItemDTOList)
    {
        return orderItemDTOList.stream().map(orderItemMapper::toEntity).toList();
    }



}

