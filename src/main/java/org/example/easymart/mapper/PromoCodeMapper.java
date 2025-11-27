package org.example.easymart.mapper;

import org.example.easymart.dto.request.PromoCodeDTO;
import org.example.easymart.dto.response.PromoCodeDtoResponse;
import org.example.easymart.entity.PromoCode;
import org.mapstruct.Mapper;

@Mapper(   componentModel = "spring")
public interface PromoCodeMapper {

    public PromoCode toEntity(PromoCodeDTO promoCodeDTO);
    public PromoCodeDtoResponse toDtoResponse(PromoCode promoCode);
}
