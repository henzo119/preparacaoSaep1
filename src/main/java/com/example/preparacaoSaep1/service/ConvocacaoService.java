package com.example.preparacaoSaep1.service;



import com.example.preparacaoSaep1.model.Convocacao;
import com.example.preparacaoSaep1.model.Selecao;
import com.example.preparacaoSaep1.repository.ConvocacaoRepository;
import com.example.preparacaoSaep1.repository.SelecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor

public class ConvocacaoService {

    private final ConvocacaoRepository convocacaoRepository;
    private final SelecaoRepository selecaoRepository;

    public Convocacao registrar(Convocacao convocacao) {
        Selecao selecao = selecaoRepository.findById(convocacao.getSelecao().getId())
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada"));
        Integer saldoAtual = selecao.getJogadoresDisponiveis();
             selecao.setJogadoresDisponiveis(saldoAtual +
                convocacao.getQuantidade());
             selecaoRepository.save(selecao);
              convocacao.setSelecao(selecao);
                 return convocacaoRepository.save(convocacao);
    }
}
