package org.example.easymart.repository;

import jakarta.transaction.Transactional;
import org.example.easymart.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
    @Transactional
    @Query(value = "UPDATE Produit  SET Produit.quantity = Produit.quantity - :quantityToSubtract WHERE Produit.id = :produitId" , nativeQuery = true)
    int subtractQuantity(@Param("produitId") Long produitId, @Param("quantityToSubtract") Long quantityToSubtract);
}
