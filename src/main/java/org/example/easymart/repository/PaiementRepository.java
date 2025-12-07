package org.example.easymart.repository;

import org.example.easymart.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {

    public Optional<Paiement> getPaiementsByCommande_Id(Long commandeId);
}
