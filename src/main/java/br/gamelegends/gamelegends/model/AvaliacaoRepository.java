package br.gamelegends.gamelegends.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    List<Avaliacao> findByNomeJogo(String nomeJogo);
    
    @Query("SELECT AVG(a.estrelas) FROM Avaliacao a WHERE a.nomeJogo = :nomeJogo")
    Double findMediaEstrelasByNomeJogo(@Param("nomeJogo") String nomeJogo);
    
    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.nomeJogo = :nomeJogo")
    Long countByNomeJogo(@Param("nomeJogo") String nomeJogo);
}
