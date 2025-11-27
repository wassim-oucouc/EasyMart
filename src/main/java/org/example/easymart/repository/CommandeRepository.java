package org.example.easymart.repository;

import org.example.easymart.entity.Commande;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {

    @Query(value = "SELECT * FROM commande_products where commande_products.product_id = :productId",nativeQuery = true)
    public List<Object[]> checkCommandesProductByProductId(@Param("productId") Long id);
}
