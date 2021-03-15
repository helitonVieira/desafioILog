package com.heliton.desafioILog.resources;

import java.net.URI;
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
import com.heliton.desafioILog.dto.CursoDTO;
import com.heliton.desafioILog.services.CursoService;

@RestController
@RequestMapping(value="/cursos")
public class CursoResource {
	
		@Autowired
		private CursoService service;
		@Autowired
		private LogSistemaResource logResouce;
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody Curso obj) {
			obj = service.insert(obj);
			logResouce.newLog("Curso", obj.getTitulo(),null,"CADASTRO");
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<Curso>> findAll() {
			List<Curso> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}	
		
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Curso> find(@PathVariable Integer id) {
			Curso obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}		
		
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody Curso obj, @PathVariable Integer id) {
			
			obj.setId(id);
			
			Curso oldObj = new Curso();
			oldObj = service.find(id);			
			logResouce.newLog("Curso", obj.getTitulo() +"New: " + obj.toString()  ,null,
					"UPDATE Objeto Old(" + "id: " +  oldObj.getId() +
					" titulo: " +  oldObj.getTitulo() + 
					" descricao: " +  oldObj.getDescricao() +
					" carga horaria: " +  oldObj.getCargaHoraria() + 
					" valor: " +  oldObj.getValor() + 
						" Objeto New (id: " +  obj.getId() +
						" titulo: " +  obj.getTitulo() + 
						" descricao: " +  obj.getDescricao() +
						" carga horaria: " +  obj.getCargaHoraria() + 
						" valor: " +  obj.getValor()+ ")") ;
			
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
	
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			
			Curso obj = new Curso();
			obj = service.find(id);
			logResouce.newLog("Curso",obj.getTitulo(),null,					
					"DELETE " +"id: " +  obj.getId() +
					" titulo: " +  obj.getTitulo() + 
					" descricao: " +  obj.getDescricao() +
					" carga horaria: " +  obj.getCargaHoraria() + 
					" valor: " +  obj.getValor());
			
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		
		

}
