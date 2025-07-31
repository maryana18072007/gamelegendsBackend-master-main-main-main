package br.gamelegends.gamelegends.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetosRepository extends JpaRepository<Projetos, Long> {
	// Método para verificar se existe um projeto com o nome informado
    boolean existsByNomeProjeto(String nomeProjeto);
}
