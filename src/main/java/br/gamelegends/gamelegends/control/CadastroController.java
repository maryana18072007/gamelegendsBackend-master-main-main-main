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

import br.gamelegends.gamelegends.config.DataValidator;
import br.gamelegends.gamelegends.model.Cadastro;
import br.gamelegends.gamelegends.service.CadastroService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/cadastro")
public class CadastroController {

    final CadastroService cadastroService;

    public CadastroController(CadastroService _cadastroService) {
        this.cadastroService = _cadastroService;
    }

    // ROTA POST - Criar novo usuário
    @PostMapping
    public ResponseEntity<Object> saveCadastro(@RequestBody Cadastro cadastro) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cadastroService.save(cadastro));
    }

    // ROTA GET - Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Cadastro>> getAllCadastro() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cadastroService.findAll());
    }

    // ROTA GET - Buscar informações do cadastro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Cadastro> getCadastroById(@PathVariable Long id) {
        return cadastroService.findById(id)
                .map(cadastro -> ResponseEntity.ok().body(cadastro))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ROTA PUT - Atualizar usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCadastro(@PathVariable Long id, @RequestBody Cadastro cadastro) {
        if (cadastro.getDatanascimento() == null || !DataValidator.isValidDate(cadastro.getDatanascimento())) {
            return ResponseEntity.badRequest().body("Data de nascimento inválida");
        }

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cadastroService.update(id, cadastro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o cadastro: " + e.getMessage());
        }
    }

    // ROTA DELETE - Excluir usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCadastro(@PathVariable long id) {
        cadastroService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}