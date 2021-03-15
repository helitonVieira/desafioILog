package com.heliton.desafioILog.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.heliton.desafioILog.domain.Curso;

public class CursoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	@NotBlank
	private String titulo;
	private String descricao;
	private Integer cargaHoraria;
	private Double valor;
	
	public CursoDTO() {		
	}

	public CursoDTO(Curso obj) {
		super();
		id = obj.getId();
		titulo = obj.getTitulo();
		descricao = obj.getDescricao();
		cargaHoraria = obj.getCargaHoraria();
		valor = obj.getValor();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	


	
	

}
