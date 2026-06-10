package com.example.preparacaoSaep1.controller;


import com.example.preparacaoSaep1.model.Selecao;
import com.example.preparacaoSaep1.repository.SelecaoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selecoes")
@RequiredArgsConstructor

public class SelecaoController {


    private final SelecaoRepository selecaoRepository;

    @GetMapping
    public List<Selecao> listar() {
        return selecaoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Selecao> cadastrar(@Valid @RequestBody Selecao
                                                     selecao) {
        return ResponseEntity.status(201).body(selecaoRepository.save(selecao));
    }


    @GetMapping("/grupo")
    public List<Selecao> listarPorGrupo() {
        return SelecaoRepository.findAllOrderByGrupo();


        }
    @GetMapping("/limites")
    public List<Selecao> listarNosLimites() {
        return selecaoRepository

                .findByJogadoresDisponiveisLessThanEqualOrJogadoresDisponiveisGreaterThanEqual(0
                        , 100);
    }


}



