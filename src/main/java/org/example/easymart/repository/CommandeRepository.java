package org.example.easymart.repository;

import org.example.easymart.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
}
