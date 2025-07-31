package br.gamelegends.gamelegends.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.gamelegends.gamelegends.model.Fatura;
import br.gamelegends.gamelegends.model.FaturaRepository;

@Service
public class FaturaService {

    final FaturaRepository faturaRepository;

    public FaturaService(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;
    }

    public List<Fatura> findAll() {
        return faturaRepository.findAll();
    }

    public Optional<Fatura> findById(Long id) {
        return faturaRepository.findById(id);
    }

    public Fatura update(Long id, Fatura novaFatura) {
        return faturaRepository.findById(id).map(fatura -> {
            fatura.setData(novaFatura.getData());
            fatura.setHora(novaFatura.getHora());
            fatura.setStatus(novaFatura.getStatus());
            fatura.setCodTrans(novaFatura.getCodTrans());
            return faturaRepository.save(fatura);
        }).orElseThrow(() -> new RuntimeException("Fatura não encontrada"));
    }

    public void delete(Long id) {
        faturaRepository.deleteById(id);
    }
    
    public Fatura save(Fatura fatura) {
        if (fatura.getCodTrans() == null || fatura.getCodTrans().isEmpty()) {
            fatura.setCodTrans(UUID.randomUUID().toString());
        }
        return faturaRepository.save(fatura);
    }
}