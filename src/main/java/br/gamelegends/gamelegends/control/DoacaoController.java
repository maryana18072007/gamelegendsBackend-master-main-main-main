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

import br.gamelegends.gamelegends.model.Doacao;
import br.gamelegends.gamelegends.service.DoacaoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/doacao")
public class DoacaoController {

    final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    // ROTA POST - Criar nova doação
    @PostMapping
    public ResponseEntity<Object> saveDoacao(@RequestBody Doacao doacao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doacaoService.save(doacao));
    }

    // ROTA GET - Listar todas as doações
    @GetMapping
    public ResponseEntity<List<Doacao>> getAllDoacao() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(doacaoService.findAll());
    }

    // ROTA GET - Buscar doação pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> getDoacaoById(@PathVariable Long id) {
        return doacaoService.findById(id)
                .map(doacao -> ResponseEntity.ok().body(doacao))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ROTA PUT - Atualizar doação existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoacao(@PathVariable Long id, @RequestBody Doacao doacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(doacaoService.update(id, doacao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar a doação: " + e.getMessage());
        }
    }

    // ROTA DELETE - Excluir doação por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDoacao(@PathVariable long id) {
        doacaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}