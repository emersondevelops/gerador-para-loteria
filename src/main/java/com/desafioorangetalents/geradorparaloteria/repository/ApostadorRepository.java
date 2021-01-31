package com.desafioorangetalents.geradorparaloteria.repository;

import com.desafioorangetalents.geradorparaloteria.model.Apostador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Integer> {
    Apostador findApostadorByEmail(String email);
}
