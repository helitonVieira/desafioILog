package com.heliton.desafioILog.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heliton.desafioILog.domain.LogSistema;
import com.heliton.desafioILog.services.LogSistemaService;

@RestController
@RequestMapping(value="/logs")
public class LogSistemaResource {
	
		@Autowired
		private LogSistemaService service;
				
		public void insert(LogSistema obj) {
			 service.insert(obj);			
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<LogSistema>> findAll() {
			List<LogSistema> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}	
		
		/*@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<LogSistema> find(@PathVariable Integer id) {
			LogSistema obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}	*/
		
		public void newLog(String tipo, String nome, LocalDateTime dataHora, String historico) {
			LogSistema log = new LogSistema();
			log.setTipo(tipo);
			log.setNome(nome);			
			
			LocalDateTime agora = LocalDateTime.now();
			log.setDataHora(agora);
			
			log.setHistorico(historico);
			insert(log);			
		}

}
