package br.gamelegends.gamelegends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gamelegends.gamelegends.model.Avaliacao;
import br.gamelegends.gamelegends.model.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public Avaliacao save(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> findById(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public Avaliacao update(Long id, Avaliacao novaAvaliacao) {
        return avaliacaoRepository.findById(id).map(avaliacao -> {
            avaliacao.setComentario(novaAvaliacao.getComentario());
            avaliacao.setEstrelas(novaAvaliacao.getEstrelas());
            avaliacao.setNomeJogo(novaAvaliacao.getNomeJogo());
            avaliacao.setNomeUsuario(novaAvaliacao.getNomeUsuario());
            avaliacao.setDataAvaliacao(novaAvaliacao.getDataAvaliacao());
            return avaliacaoRepository.save(avaliacao);
        }).orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
    }

    public void delete(Long id) {
        avaliacaoRepository.deleteById(id);
    }
    
    public List<Avaliacao> findByNomeJogo(String nomeJogo) {
        return avaliacaoRepository.findByNomeJogo(nomeJogo);
    }
    
    public Double getMediaEstrelas(String nomeJogo) {
        return avaliacaoRepository.findMediaEstrelasByNomeJogo(nomeJogo);
    }
    
    public Long getQuantidadeAvaliacoes(String nomeJogo) {
        return avaliacaoRepository.countByNomeJogo(nomeJogo);
    }
}