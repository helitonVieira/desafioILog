package com.heliton.desafioILog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heliton.desafioILog.domain.Curso;
import com.heliton.desafioILog.dto.CursoDTO;
import com.heliton.desafioILog.repositories.CursoRepository;
import com.heliton.desafioILog.services.exception.DataIntegrityException;
import com.heliton.desafioILog.services.exception.ObjectNotFoundException;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository repo;

	public Curso find(Integer id) {
		Optional<Curso> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Curso.class.getName()));
	}

	public Curso insert(Curso obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Curso update(Curso obj) {
		Curso newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir, existem outras amaração");
		}
	}

	public List<Curso> findAll() {
		return repo.findAllByOrderByTitulo();
	}

	/*public Page<SubCategoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	} //import org.springframework.data.domain.Sort.Direction; 

	public Curso fromDTO(CursoDTO objDto) {
		Curso func = new Curso(null, objDto.getNome(),objDto.getEndereco(),objDto.getDtaAdmissao());
		func.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			func.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			func.getTelefones().add(objDto.getTelefone3());
		}
		return func;
	}*/
	
	private void updateData(Curso newObj, Curso obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setDescricao(obj.getDescricao());
		newObj.setCargaHoraria(obj.getCargaHoraria());
		newObj.setValor(obj.getValor());
	}
	
	
}
