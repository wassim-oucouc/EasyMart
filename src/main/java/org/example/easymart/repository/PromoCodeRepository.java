package org.example.easymart.repository;

import org.example.easymart.entity.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PromoCodeRepository extends JpaRepository<PromoCode,Long> {

    Optional<PromoCode> getPromoCodeByCode(String code);
}
