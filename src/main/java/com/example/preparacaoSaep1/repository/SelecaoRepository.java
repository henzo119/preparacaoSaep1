package com.example.preparacaoSaep1.repository;

import com.example.preparacaoSaep1.model.Selecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SelecaoRepository extends JpaRepository<Selecao, Long> {

    @Query("SELECT s FROM Selecao s ORDER BY s.grupo ASC, s.nome ASC")
    static List<Selecao> findAllOrderByGrupo() {
        return null;
    }

    List<Selecao>
    findByJogadoresDisponiveisLessThanEqualOrJogadoresDisponiveisGreaterThanEqual(Integer min, Integer max);

}
