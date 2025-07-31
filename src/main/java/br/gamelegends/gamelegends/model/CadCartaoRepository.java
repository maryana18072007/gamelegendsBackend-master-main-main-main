package br.gamelegends.gamelegends.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CadCartaoRepository extends JpaRepository<CadCartao, Long> {
	List<CadCartao> findByClienteId(Long clienteId);
}
