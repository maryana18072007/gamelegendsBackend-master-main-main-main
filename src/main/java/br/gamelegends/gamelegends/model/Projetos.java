package br.gamelegends.gamelegends.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="projetos")
public class Projetos {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomeProjeto;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false)
    private String dataInicio;

    @Column(nullable = false)
    private String tecnologias;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = true)
    private String imagemUrl; // Caminho relativo para o arquivo salvo
    
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    
    // Getters e Setters
    // ...
    
    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }

	public String getImagemUrl() {
		return imagemUrl;
	}
	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(String tecnologias) {
		this.tecnologias = tecnologias;
	}
	private String statusProjeto;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getStatusProjeto() {
        return statusProjeto;
    }
    public void setStatusProjeto(String statusProjeto) {
        this.statusProjeto = statusProjeto;
    }
    
 // Implementação do método toString()
    @Override
    public String toString() {
        return "Projetos{" +
                "id=" + id +
                ", nomeProjeto='" + nomeProjeto + '\'' +
                ", descricao='" + descricao + '\'' +
                ", genero='" + genero + '\'' +
                ", tecnologias='" + tecnologias + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                /* ", foto=" + (foto != null ? "tamanho: " + foto.length : "null") + */
                '}';
    }
}


