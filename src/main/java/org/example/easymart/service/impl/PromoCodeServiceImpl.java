package org.example.easymart.service.impl;

import org.example.easymart.dto.response.PromoCodeDtoResponse;
import org.example.easymart.entity.PromoCode;
import org.example.easymart.exception.PromoCodeNotFoundException;
import org.example.easymart.mapper.PromoCodeMapper;
import org.example.easymart.repository.PromoCodeRepository;
import org.example.easymart.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PromoCodeServiceImpl implements PromoCodeService {

    private PromoCodeRepository promoCodeRepository;
    private PromoCodeMapper promoCodeMapper;

    @Autowired
    public PromoCodeServiceImpl(PromoCodeRepository promoCodeRepository, PromoCodeMapper promoCodeMapper)
    {
        this.promoCodeRepository = promoCodeRepository;
        this.promoCodeMapper = promoCodeMapper;
    }

    public PromoCodeDtoResponse getPromoCodeByCode(String code)
    {
        PromoCode promoCode = this.promoCodeRepository.getPromoCodeByCode(code).orElseThrow(() -> new PromoCodeNotFoundException("Promo Code Not Exists code : " + code));
        return this.promoCodeMapper.toDtoResponse(promoCode);
    }
}
