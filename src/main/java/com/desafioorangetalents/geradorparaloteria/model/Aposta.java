package com.desafioorangetalents.geradorparaloteria.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String numeros = gerarNumeros();

    private LocalDateTime criacao = LocalDateTime.now();

    @ManyToOne
    private Apostador apostador;

    private String gerarNumeros() {
        Random random = new Random();
        return random
                .ints(1, 61)
                .distinct()
                .limit(6)
                .boxed()
                .sorted()
                .collect(Collectors.toList())
                .toString();
    }
}
