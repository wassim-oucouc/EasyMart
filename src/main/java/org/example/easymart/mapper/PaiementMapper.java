package org.example.easymart.mapper;


import org.example.easymart.dto.request.PaiementDTO;
import org.example.easymart.dto.response.PaiementDtoResponse;
import org.example.easymart.entity.Paiement;
import org.mapstruct.Mapper;

@Mapper(   componentModel = "spring",
        uses = {CommandeMapper.class})
public interface PaiementMapper {

    public Paiement toEntity(PaiementDTO paiementDTO);
    public PaiementDtoResponse toDtoResponse(Paiement paiement);
}
