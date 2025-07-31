package br.gamelegends.gamelegends.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String comentario;
    private int estrelas; // 1 a 5 estrelas
    private String nomeJogo;
    private String nomeUsuario;
    private String dataAvaliacao;

    public Avaliacao() {}

    public Avaliacao(String comentario, int estrelas, String nomeJogo, String nomeUsuario, String dataAvaliacao) {
        this.comentario = comentario;
        this.estrelas = estrelas;
        this.nomeJogo = nomeJogo;
        this.nomeUsuario = nomeUsuario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public int getEstrelas() {
        return estrelas;
    }
    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }
    public String getNomeJogo() {
        return nomeJogo;
    }
    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    public String getDataAvaliacao() {
        return dataAvaliacao;
    }
    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}