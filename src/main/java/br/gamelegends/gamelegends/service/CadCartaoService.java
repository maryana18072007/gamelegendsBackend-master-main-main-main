package br.gamelegends.gamelegends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gamelegends.gamelegends.model.CadCartao;
import br.gamelegends.gamelegends.model.CadCartaoRepository;

@Service
public class CadCartaoService {

    final CadCartaoRepository cadCartaoRepository;

    public CadCartaoService(CadCartaoRepository cadCartaoRepository) {
        this.cadCartaoRepository = cadCartaoRepository;
    }

    public CadCartao save(CadCartao cartao) {
        return cadCartaoRepository.save(cartao);
    }

    public List<CadCartao> findAll() {
        return cadCartaoRepository.findAll();
    }

    public Optional<CadCartao> findById(Long id) {
        return cadCartaoRepository.findById(id);
    }

    public CadCartao update(Long id, CadCartao novoCartao) {
        return cadCartaoRepository.findById(id).map(cartao -> {
            cartao.setNomeT(novoCartao.getNomeT());
            cartao.setNumC(novoCartao.getNumC());
            cartao.setValidade(novoCartao.getValidade());
            cartao.setCVV(novoCartao.getCVV());
            cartao.setBandeira(novoCartao.getBandeira());
            return cadCartaoRepository.save(cartao);
        }).orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
    }

    public void delete(Long id) {
        cadCartaoRepository.deleteById(id);
    }
    
    public List<CadCartao> findByClienteId(Long clienteId) {
        return cadCartaoRepository.findByClienteId(clienteId);
    }
}