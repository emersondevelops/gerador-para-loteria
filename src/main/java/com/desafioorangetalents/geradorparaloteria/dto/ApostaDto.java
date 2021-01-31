package com.desafioorangetalents.geradorparaloteria.dto;

import com.desafioorangetalents.geradorparaloteria.model.Aposta;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ApostaDto {

    String numeros;
    String criacao;

    public ApostaDto(Aposta aposta) {
        this.numeros = aposta.getNumeros();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.criacao = formatter.format(aposta.getCriacao());
    }
}
