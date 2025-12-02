package org.example.easymart.service;

import org.example.easymart.enumeration.CustomerTier;

import java.math.BigDecimal;

public interface DiscountService {

    public BigDecimal getRemiseByStatus(CustomerTier customerTier,BigDecimal sous_total);
}
