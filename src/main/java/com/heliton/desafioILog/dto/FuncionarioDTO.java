package com.heliton.desafioILog.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heliton.desafioILog.domain.Funcionario;


public class FuncionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	
	@NotBlank	
	@Length(min=1, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")	
	private String nome;
	
	private String telefone1;

	private String telefone2;
	
	private String telefone3;
	
    private String endereco;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dtaAdmissao; 	
	
	public FuncionarioDTO() {	
		
	}	
	
	
	public FuncionarioDTO(Funcionario obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		endereco = obj.getEndereco();
		dtaAdmissao = obj.getDtaAdmissao();	
		
		Iterator<String> tel = obj.getTelefones().iterator();
		while(tel.hasNext()) {
			if(this.telefone1 != null && this.telefone2 != null && this.telefone3 == null) {
				this.telefone3 = tel.next();
			}else if(this.telefone1 != null && this.telefone2 == null ) {
				this.telefone2 = tel.next();
			}else if(this.telefone1 == null) {
				this.telefone1 = tel.next();
			}									
		}			
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
	
}
