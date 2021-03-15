package com.heliton.desafioILog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heliton.desafioILog.domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
	@Transactional(readOnly=true)
	public List<Curso> findAllByOrderByTitulo();
}
