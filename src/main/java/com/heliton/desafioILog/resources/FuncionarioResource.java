package com.heliton.desafioILog.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.heliton.desafioILog.domain.Funcionario;
import com.heliton.desafioILog.dto.FuncionarioDTO;
import com.heliton.desafioILog.services.FuncionarioService;

@RestController
@RequestMapping(value="/funcionarios")
public class FuncionarioResource {
	
		@Autowired
		private FuncionarioService service;
		
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Funcionario> find(@PathVariable Integer id) {
			Funcionario obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}
		
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody Funcionario objDto) {
			//Funcionario obj = service.fromDTO(objDto);
			//obj = service.insert(obj);
			objDto = service.insert(objDto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(objDto.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@RequestBody FuncionarioDTO objDto, @PathVariable Integer id) {
			Funcionario obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
	
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<FuncionarioDTO>> findAll() {
			List<Funcionario> list = service.findAll();
			List<FuncionarioDTO> listDto = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());  
			return ResponseEntity.ok().body(listDto);
		}
		
		/*@RequestMapping(value="/page", method=RequestMethod.GET)
		public ResponseEntity<Page<FuncionarioDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
				@RequestParam(value="direction", defaultValue="ASC") String direction) {
			Page<Funcionario> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<FuncionarioDTO> listDto = list.map(obj -> new FuncionarioDTO(obj));  
			return ResponseEntity.ok().body(listDto);
		}*/

}
