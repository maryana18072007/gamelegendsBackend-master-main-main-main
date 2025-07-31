package br.gamelegends.gamelegends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gamelegends.gamelegends.model.Doacao;
import br.gamelegends.gamelegends.model.DoacaoRepository;

@Service
public class DoacaoService {

    final DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    public Doacao save(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    public List<Doacao> findAll() {
        return doacaoRepository.findAll();
    }

    public Optional<Doacao> findById(Long id) {
        return doacaoRepository.findById(id);
    }

    public Doacao update(Long id, Doacao novaDoacao) {
        return doacaoRepository.findById(id).map(doacao -> {
            doacao.setValor(novaDoacao.getValor());
            doacao.setConfirm(novaDoacao.getConfirm());
            return doacaoRepository.save(doacao);
        }).orElseThrow(() -> new RuntimeException("Doação não encontrada"));
    }

    public void delete(Long id) {
        doacaoRepository.deleteById(id);
    }
}