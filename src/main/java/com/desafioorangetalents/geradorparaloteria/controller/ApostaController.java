package com.desafioorangetalents.geradorparaloteria.controller;

import com.desafioorangetalents.geradorparaloteria.dto.ApostaDto;
import com.desafioorangetalents.geradorparaloteria.dto.ApostasPorApostadorDto;
import com.desafioorangetalents.geradorparaloteria.model.Aposta;
import com.desafioorangetalents.geradorparaloteria.model.Apostador;
import com.desafioorangetalents.geradorparaloteria.repository.ApostaRepository;
import com.desafioorangetalents.geradorparaloteria.repository.ApostadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/aposta")
@Validated
public class ApostaController {

    private final ApostaRepository apostaRepository;
    private final ApostadorRepository apostadorRepository;

    public ApostaController(ApostaRepository apostaRepository, ApostadorRepository apostadorRepository) {
        this.apostaRepository = apostaRepository;
        this.apostadorRepository = apostadorRepository;
    }

    @PostMapping("/nova")
    ResponseEntity<?> novaAposta(@RequestParam(value = "email") @Email String email) {

        Apostador apostador = apostadorRepository.findApostadorByEmail(email);
        Aposta novaAposta = new Aposta();

        if (apostador == null) {
            Apostador novoApostador = new Apostador(email);
            apostadorRepository.save(novoApostador);
            novaAposta.setApostador(novoApostador);
        } else {
            List<Aposta> apostas = apostaRepository.findAllByApostadorOrderByCriacaoDesc(apostador);
            boolean numerosJaApostados = true;
            while (numerosJaApostados) {
                for (Aposta aposta : apostas) {
                    if (aposta.getNumeros().equals(novaAposta.getNumeros())) {
                        novaAposta = new Aposta();
                    } else {
                        numerosJaApostados = false;
                    }
                }
            }
            novaAposta.setApostador(apostador);
        }

        apostaRepository.save(novaAposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApostaDto(novaAposta));
    }

    @GetMapping("/listar")
    ResponseEntity<?> listarApostas(@RequestParam(value = "email") @Email String email) {

        Apostador apostador = apostadorRepository.findApostadorByEmail(email);

        if (apostador != null) {
            List<Aposta> apostas = apostaRepository.findAllByApostadorOrderByCriacaoDesc(apostador);
            return ResponseEntity.ok(new ApostasPorApostadorDto(apostador, apostas));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Nenhuma aposta foi encontrada associada ao e-mail: " + email);
    }
}
