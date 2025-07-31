package br.gamelegends.gamelegends.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.gamelegends.gamelegends.model.Projetos;
import br.gamelegends.gamelegends.model.ProjetosRepository;
import jakarta.transaction.Transactional;

@Service
public class ProjetosService {

    final ProjetosRepository projetosRepository;

    public ProjetosService(ProjetosRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    // Lista todos os projetos
    public List<Projetos> findAll() {
        return projetosRepository.findAll();
    }

    // Salva um projeto (usado para criar sem imagem)
    @Transactional
    public Projetos save(Projetos projetos) {
        return projetosRepository.save(projetos);
    }

    // Verifica se já existe projeto pelo nome
    public boolean existsByName(String nomeProjeto) {
        return projetosRepository.existsByNomeProjeto(nomeProjeto);
    }

    // Cria projeto com OU sem foto via campos separados (usado pelo controller)
    @Transactional
    public Projetos createComFotoOuNao(
            String nomeProjeto,
            String descricao,
            String dataInicio,
            String tecnologias,
            String genero,
            MultipartFile foto) throws IOException {

        Projetos projeto = new Projetos();
        projeto.setNomeProjeto(nomeProjeto);
        projeto.setDescricao(descricao);
        projeto.setDataInicio(dataInicio);
        projeto.setTecnologias(tecnologias);
        projeto.setGenero(genero);

        // Salva o binário no banco (byte[]) - NÃO salva no disco!
        if (foto != null && !foto.isEmpty()) {
            // NUNCA chame transferTo se vai salvar no banco!
            projeto.setFoto(foto.getBytes());
        }

        return projetosRepository.save(projeto);
    }

    // (Opcional, caso queira uso antigo do padrão com ModelAttribute)
    @Transactional
    public Projetos createComFoto(MultipartFile foto, Projetos projeto) {
        if (projeto == null) {
            throw new IllegalArgumentException("O projeto não pode ser nulo.");
        }
        if (foto != null && !foto.isEmpty()) {
            // NUNCA chame transferTo se vai salvar no banco!
            try {
                projeto.setFoto(foto.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar a foto: " + e.getMessage());
            }
        }
        return projetosRepository.save(projeto);
    }
}