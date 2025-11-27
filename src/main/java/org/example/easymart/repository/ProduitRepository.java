package org.example.easymart.repository;

import org.example.easymart.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
