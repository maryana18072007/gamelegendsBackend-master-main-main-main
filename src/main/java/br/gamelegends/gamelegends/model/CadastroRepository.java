package br.gamelegends.gamelegends.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
    // Busca um cadastro pelo email
    Cadastro findByEmail(String email);
    Cadastro findBySenha(String senha);
}
