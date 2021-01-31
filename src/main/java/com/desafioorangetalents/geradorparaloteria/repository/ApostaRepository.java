package com.desafioorangetalents.geradorparaloteria.repository;

import com.desafioorangetalents.geradorparaloteria.model.Aposta;
import com.desafioorangetalents.geradorparaloteria.model.Apostador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Integer> {
    List<Aposta> findAllByApostadorOrderByCriacaoDesc(Apostador apostador);
}
