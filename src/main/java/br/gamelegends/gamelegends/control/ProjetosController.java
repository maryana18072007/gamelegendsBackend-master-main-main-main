package br.gamelegends.gamelegends.control;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.gamelegends.gamelegends.model.Projetos;
import br.gamelegends.gamelegends.model.ProjetosRepository;
import br.gamelegends.gamelegends.rest.response.MessageResponse;
import br.gamelegends.gamelegends.service.ProjetosService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/projetos")
public class ProjetosController {

    final ProjetosService projetosService;
    final ProjetosRepository projetosRepository;

    public ProjetosController(ProjetosService projetosService, ProjetosRepository projetosRepository) {
        this.projetosService = projetosService;
        this.projetosRepository = projetosRepository;
    }

    // GET: Retorna todos os projetos
    @GetMapping
    public ResponseEntity<List<Projetos>> getAllProjetos() {
        return ResponseEntity.ok(projetosService.findAll());
    }

    // GET: Retorna o binário da foto do projeto por ID (byte[])
    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> getFotoProjeto(@PathVariable Long id) {
        Optional<Projetos> projetoOpt = projetosRepository.findById(id);
        if (projetoOpt.isPresent() && projetoOpt.get().getFoto() != null) {
            byte[] imagem = projetoOpt.get().getFoto();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // ou MediaType.IMAGE_PNG se for o caso
            return new ResponseEntity<>(imagem, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST: Cria um projeto com ou sem foto (campo correto: foto)
    @PostMapping("/createComFoto")
    public ResponseEntity<?> createProjeto(
       @RequestParam("nomeProjeto") String nomeProjeto,
       @RequestParam("descricao") String descricao,
       @RequestParam("dataInicio") String dataInicio,
       @RequestParam("tecnologias") String tecnologias,
       @RequestParam("genero") String genero,
       @RequestParam(value = "foto", required = false) MultipartFile foto) {

        if (projetosService.existsByName(nomeProjeto)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Projeto já cadastrado!"));
        }
        try {
            Projetos projetoCriado = projetosService.createComFotoOuNao(
                nomeProjeto, descricao, dataInicio, tecnologias, genero, foto
            );
            return ResponseEntity.ok().body(projetoCriado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erro ao cadastrar projeto: " + e.getMessage()));
        }
    }

    // POST: Cria um projeto sem foto (caso precise)
    @PostMapping("/createSemFoto")
    public ResponseEntity<?> createSemFoto(@RequestBody Projetos projetos) {
        if (projetosService.existsByName(projetos.getNomeProjeto())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Projeto já cadastrado!"));
        }
        projetosService.save(projetos);
        return ResponseEntity.ok().body(new MessageResponse("Projeto cadastrado com sucesso!"));
    }

    // POST: Salva um novo projeto com validações (JSON puro, sem foto)
    @PostMapping
    public ResponseEntity<Object> saveProjetos(@RequestBody Projetos projetos) {
        // Validação de campos obrigatórios
        if (projetos.getNomeProjeto() == null || projetos.getNomeProjeto().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome do projeto é obrigatório.");
        }
        if (projetos.getDescricao() == null || projetos.getDescricao().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A descrição do projeto é obrigatória.");
        }
        if (projetos.getDataInicio() == null || projetos.getDataInicio().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início é obrigatória.");
        }
        if (projetos.getTecnologias() == null || projetos.getTecnologias().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As tecnologias são obrigatórias.");
        }
        if (projetos.getGenero() == null || projetos.getGenero().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O gênero do projeto é obrigatório.");
        }

        // Salva o projeto no banco de dados
        return ResponseEntity.status(HttpStatus.CREATED).body(projetosService.save(projetos));
    }
}