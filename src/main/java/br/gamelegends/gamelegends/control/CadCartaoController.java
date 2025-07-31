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

import br.gamelegends.gamelegends.model.CadCartao;
import br.gamelegends.gamelegends.service.CadCartaoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/cadcartao")
public class CadCartaoController {

    final CadCartaoService cadCartaoService;

    public CadCartaoController(CadCartaoService cadCartaoService) {
        this.cadCartaoService = cadCartaoService;
    }

    // ROTA POST - Criar novo cartão
    @PostMapping
    public ResponseEntity<Object> saveCartao(@RequestBody CadCartao cartao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cadCartaoService.save(cartao));
    }

    // ROTA GET - Listar todos os cartões
    @GetMapping
    public ResponseEntity<List<CadCartao>> getAllCartao() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cadCartaoService.findAll());
    }

    // ROTA GET - Buscar cartão pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<CadCartao> getCartaoById(@PathVariable Long id) {
        return cadCartaoService.findById(id)
                .map(cartao -> ResponseEntity.ok().body(cartao))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ROTA PUT - Atualizar cartão existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartao(@PathVariable Long id, @RequestBody CadCartao cartao) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cadCartaoService.update(id, cartao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o cartão: " + e.getMessage());
        }
    }

    // ROTA DELETE - Excluir cartão por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCartao(@PathVariable Long id) {
        cadCartaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    // ROTA GET - Buscar cartões por cliente ID
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CadCartao>> getCartoesByCliente(@PathVariable Long clienteId) {
        List<CadCartao> cartoes = cadCartaoService.findByClienteId(clienteId);
        return ResponseEntity.status(HttpStatus.OK).body(cartoes);
    }
    
    // ROTA GET - Teste de conexão
    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        return ResponseEntity.ok("Sistema de cartões funcionando!");
    }
}