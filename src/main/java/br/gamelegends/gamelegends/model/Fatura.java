package br.gamelegends.gamelegends.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Fatura")
public class Fatura {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private String Data;
	private String Hora;
    private String Status;
    private String CodTrans;
    
    public Fatura() {
        // Gera automaticamente um UUID se o CodTrans for nulo
        this.CodTrans = UUID.randomUUID().toString();
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getHora() {
		return Hora;
	}
	public void setHora(String hora) {
		Hora = hora;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCodTrans() {
		return CodTrans;
	}
	public void setCodTrans(String codTrans) {
		CodTrans = codTrans;
	}
	
}
