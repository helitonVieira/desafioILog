package com.heliton.desafioILog.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="logSistema")
public class LogSistema  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String tipo;
	private String nome;
	private LocalDateTime dataHora;
	private String historico;
	
	public LogSistema() {
		
	}

	public LogSistema(String id, String tipo, String nome, LocalDateTime dataHora, String historico) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.dataHora = dataHora;
		this.historico = historico;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}
	
	
	

}
