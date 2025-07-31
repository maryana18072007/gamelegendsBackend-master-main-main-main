package br.gamelegends.gamelegends.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CadCartao")
public class CadCartao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "nomeT")
	private String nomeT;
	
	@Column(name = "numC")
	private String numC;
	
	@Column(name = "validade")
	private String validade;
	
	@Column(name = "CVV", nullable = true)
	private String CVV;
	
	@Column(name = "bandeira")
	private String bandeira;
	
	@Column(name = "clienteId")
	private Long clienteId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeT() {
		return nomeT;
	}
	public void setNomeT(String nomeT) {
		this.nomeT = nomeT;
	}
	public String getNumC() {
		return numC;
	}
	public void setNumC(String numC) {
		this.numC = numC;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
	public Long getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

}
