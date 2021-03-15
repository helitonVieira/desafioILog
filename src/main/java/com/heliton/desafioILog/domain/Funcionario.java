package com.heliton.desafioILog.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	private String nome;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	private String endereco;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dtaAdmissao; 
	
	@JsonIgnore
	@ManyToMany
	 @JoinTable(name = "FUNCIONARIO_CURSO", 
	 joinColumns = @JoinColumn(name = "funcionario_id"), 
	 inverseJoinColumns = @JoinColumn(name = "curso_id") 
	 ) 
	private List<Curso> cursos = new ArrayList<>(); 

	
	public Funcionario() {		
	}
	
	public Funcionario(Integer id, String nome, String endereco, LocalDateTime dtaAdmissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.dtaAdmissao = dtaAdmissao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}	

	public LocalDateTime getDtaAdmissao() {
		return dtaAdmissao;
	}

	public void setDtaAdmissao(LocalDateTime dtaAdmissao) {
		this.dtaAdmissao = dtaAdmissao;
	}	
	

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
