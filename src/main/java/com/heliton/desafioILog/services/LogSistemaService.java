package com.heliton.desafioILog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heliton.desafioILog.domain.LogSistema;
import com.heliton.desafioILog.repositories.LogSistemaRepository;
import com.heliton.desafioILog.services.exception.ObjectNotFoundException;

@Service
public class LogSistemaService {
	
	@Autowired
	private LogSistemaRepository repo;

	public LogSistema find(Integer id) {
		Optional<LogSistema> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + LogSistema.class.getName()));
	}

	public void insert(LogSistema obj) {
		 repo.save(obj);
	}	

	public List<LogSistema> findAll() {
		return repo.findAll();
	}	

}
