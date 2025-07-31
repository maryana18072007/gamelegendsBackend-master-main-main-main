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

import br.gamelegends.gamelegends.model.Fatura;
import br.gamelegends.gamelegends.service.FaturaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/fatura")
public class FaturaController {

    final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    // ROTA POST - Criar nova fatura
    @PostMapping
    public ResponseEntity<Object> saveFatura(@RequestBody Fatura fatura) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(faturaService.save(fatura));
    }

    // ROTA GET - Listar todas as faturas
    @GetMapping
    public ResponseEntity<List<Fatura>> getAllFatura() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(faturaService.findAll());
    }

    // ROTA GET - Buscar fatura pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Fatura> getFaturaById(@PathVariable Long id) {
        return faturaService.findById(id)
                .map(fatura -> ResponseEntity.ok().body(fatura))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ROTA PUT - Atualizar fatura existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFatura(@PathVariable Long id, @RequestBody Fatura fatura) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(faturaService.update(id, fatura));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar a fatura: " + e.getMessage());
        }
    }

    // ROTA DELETE - Excluir fatura por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFatura(@PathVariable long id) {
        faturaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}