package com.heliton.desafioILog.resources;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.heliton.desafioILog.domain.Curso;
import com.heliton.desafioILog.domain.Funcionario;
import com.heliton.desafioILog.dto.FuncionarioDTO;
import com.heliton.desafioILog.services.FuncionarioService;

@RestController
@RequestMapping(value="/funcionarios")
public class FuncionarioResource {
	
		@Autowired
		private FuncionarioService service;
		@Autowired
		private LogSistemaResource logResouce;
		
		private String telefone1;
		private String telefone2;
		private String telefone3;
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody FuncionarioDTO objDto) {
			Funcionario obj = service.fromDTO(objDto);
			obj = service.insert(obj);			
			logResouce.newLog("Funcionario", obj.getNome(),null,"CADASTRO");			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<FuncionarioDTO>> findAll() {
			List<Funcionario> list = service.findAll();
			List<FuncionarioDTO> listDto = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());  
			return ResponseEntity.ok().body(listDto);
		}	
		
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Funcionario> find(@PathVariable Integer id) {
			Funcionario obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}		
		
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody FuncionarioDTO objDto, @PathVariable Integer id) {
			Funcionario obj = service.fromDTO(objDto);
			obj.setId(id);
			
			Funcionario oldObj = new Funcionario();			
			oldObj = service.find(id);		
			
			logResouce.newLog("Funcionario", obj.getNome() +"New: " + obj.toString()  ,null,
					"UPDATE Objeto Old(" + "id: " +  oldObj.getId() +
					" nome: " +  oldObj.getNome() + 
					" endereco: " +  oldObj.getEndereco() +
					" dtaAdmissao: " +  oldObj.getDtaAdmissao() + 
					" telefones: " +  telefones(oldObj) + 
						" Objeto New (id: " +  obj.getId() +
						" nome: " +  obj.getNome() + 
						" endereco: " +  obj.getEndereco() +
						" dtaAdmissao: " +  obj.getDtaAdmissao() + 
						" telefones: " +  telefones(obj) + ")") ;
			
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
	
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			
			Funcionario obj = new Funcionario();			
			obj = service.find(id);
			logResouce.newLog("Funcionario", obj.getNome() +"New: " + obj.toString()  ,null,
					"DELETE(" + "id: " +  obj.getId() +
					" nome: " +  obj.getNome() + 
					" endereco: " +  obj.getEndereco() +
					" dtaAdmissao: " +  obj.getDtaAdmissao() + 
					" telefones: " +  telefones(obj) + ")") ;
			
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		private String telefones(Funcionario obj) {		
			String fones = "";
			Iterator<String> tel = obj.getTelefones().iterator();
			while(tel.hasNext()) {
				fones = fones +" " + tel.next();								
			}	
			return fones;
		}
		
		
		

}
