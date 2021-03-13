package com.heliton.desafioILog.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.heliton.desafioILog.domain.Funcionario;

public class FuncionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	//private Set<String> telefones = new HashSet<>();
	
	private String telefone;

	//private String telefone2;
	
	//private String telefone3;
	
    private String endereco;
	
	private Date dtaAdmissao; 
	
	public FuncionarioDTO() {
		
	}

	public FuncionarioDTO(Funcionario obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		telefone = obj.getTelefone();		
		endereco = obj.getEndereco();
		dtaAdmissao = obj.getDtaAdmissao();
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
	
	/*public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}


	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}*/
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDtaAdmissao() {
		return dtaAdmissao;
	}

	public void setDtaAdmissao(Date dtaAdmissao) {
		this.dtaAdmissao = dtaAdmissao;
	}	
}
