package com.desafioorangetalents.geradorparaloteria.dto;

import com.desafioorangetalents.geradorparaloteria.model.Aposta;
import com.desafioorangetalents.geradorparaloteria.model.Apostador;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApostasPorApostadorDto {

    private final String apostador;
    private final List<ApostaDto> apostas = new ArrayList<>();

    public ApostasPorApostadorDto(Apostador apostador, List<Aposta> listaDeApostas) {
        this.apostador = apostador.getEmail();

        for (Aposta aposta : listaDeApostas) {
            apostas.add(new ApostaDto(aposta));
        }
    }
}
