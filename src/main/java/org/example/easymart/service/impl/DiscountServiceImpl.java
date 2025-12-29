package org.example.easymart.service.impl;

import org.example.easymart.enumeration.CustomerTier;
import org.example.easymart.service.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.example.easymart.enumeration.CustomerTier.GOLD;
import static org.example.easymart.enumeration.CustomerTier.PLATINUM;

@Service
public class DiscountServiceImpl implements DiscountService {

    public BigDecimal getRemiseByStatus(CustomerTier customerTier,BigDecimal sous_total)
    {
        BigDecimal remise = new BigDecimal(0);
        switch (customerTier)
        {
            case BASIC :
               remise = BigDecimal.valueOf(0.00);
                break;
            case SILVER:
                if(sous_total.compareTo(BigDecimal.valueOf(500)) >= 0) {
                    remise = BigDecimal.valueOf(0.05);
                }
                    else {
                    remise = BigDecimal.valueOf(0);
                }
                    break;
            case GOLD :
                if(sous_total.compareTo(BigDecimal.valueOf(800)) >= 0){
                    remise =  BigDecimal.valueOf(0.10);}
                else {
                  remise =   BigDecimal.valueOf(0);}
                break;
            case PLATINUM:
                if(sous_total.compareTo(BigDecimal.valueOf(1200)) >= 0){
                    remise =  BigDecimal.valueOf(0.15);}
                else {
                    remise =   BigDecimal.valueOf(0);}
            break;
            default :
                remise =   BigDecimal.valueOf(0);


        }
        return remise;
    }
}
