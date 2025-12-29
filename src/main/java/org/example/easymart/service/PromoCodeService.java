package org.example.easymart.service;

import org.example.easymart.dto.response.PromoCodeDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface PromoCodeService {
    public PromoCodeDtoResponse getPromoCodeByCode(String code);
}
