package com.example.preparacaoSaep1.service;

import com.example.preparacaoSaep1.model.Partida;
import com.example.preparacaoSaep1.model.Selecao;
import com.example.preparacaoSaep1.repository.PartidaRepository;
import com.example.preparacaoSaep1.repository.SelecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final SelecaoRepository selecaoRepository;

    public Partida registrar(Partida partida) {

        Selecao selecao = selecaoRepository.findById(partida.getSelecao().getId())
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada"));

        if (selecao.getJogadoresDisponiveis() < partida.getQuantidade()) {
            throw new RuntimeException("Quantidade indisponível de jogadores");
        }
        selecao.setJogadoresDisponiveis(
                selecao.getJogadoresDisponiveis() - partida.getQuantidade()
        );

        selecaoRepository.save(selecao);
        partida.setSelecao(selecao);
        return partidaRepository.save(partida);
    }
}