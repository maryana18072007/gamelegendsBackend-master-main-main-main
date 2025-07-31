package br.gamelegends.gamelegends.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gamelegends.gamelegends.model.Avaliacao;
import br.gamelegends.gamelegends.service.AvaliacaoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService _avaliacaoService) {
        this.avaliacaoService = _avaliacaoService;
    }

    // ROTA POST - Criar nova avaliação
    @PostMapping
    public ResponseEntity<Object> saveAvaliacao(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avaliacaoService.save(avaliacao));
    }

    // ROTA GET - Listar todas as avaliações
    @GetMapping
    public ResponseEntity<List<Avaliacao>> getAllAvaliacao() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(avaliacaoService.findAll());
    }

    // ROTA GET - Buscar avaliação pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable Long id) {
        return avaliacaoService.findById(id)
                .map(avaliacao -> ResponseEntity.ok().body(avaliacao))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ROTA PUT - Atualizar avaliação existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(avaliacaoService.update(id, avaliacao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar a avaliação: " + e.getMessage());
        }
    }

    // ROTA DELETE - Excluir avaliação por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAvaliacao(@PathVariable long id) {
        avaliacaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    // ROTA GET - Buscar avaliações por nome do jogo
    @GetMapping("/jogo/{nomeJogo}")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByJogo(@PathVariable String nomeJogo) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(avaliacaoService.findByNomeJogo(nomeJogo));
    }
    
    // ROTA GET - Buscar média de estrelas por jogo
    @GetMapping("/media/{nomeJogo}")
    public ResponseEntity<Double> getMediaEstrelasByJogo(@PathVariable String nomeJogo) {
        Double media = avaliacaoService.getMediaEstrelas(nomeJogo);
        return ResponseEntity.status(HttpStatus.OK).body(media != null ? media : 0.0);
    }
}