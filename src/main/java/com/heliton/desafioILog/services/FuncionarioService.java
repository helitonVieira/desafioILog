package com.heliton.desafioILog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heliton.desafioILog.domain.Funcionario;
import com.heliton.desafioILog.dto.FuncionarioDTO;
import com.heliton.desafioILog.repositories.FuncionarioRepository;
import com.heliton.desafioILog.services.exception.DataIntegrityException;
import com.heliton.desafioILog.services.exception.ObjectNotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repo;

	public Funcionario find(Integer id) {
		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
	}

	public Funcionario insert(Funcionario obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Funcionario update(Funcionario obj) {
		Funcionario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir Funcionário que esta amarrado a um Curso");
		}
	}

	public List<Funcionario> findAll() {
		return repo.findAll();
	}

	/*public Page<SubCategoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	} //import org.springframework.data.domain.Sort.Direction; */

	public Funcionario fromDTO(FuncionarioDTO objDto) {
		Funcionario func = new Funcionario(null, objDto.getNome(),objDto.getEndereco(),objDto.getDtaAdmissao());
		func.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			func.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			func.getTelefones().add(objDto.getTelefone3());
		}
		return func;
	}
	
	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEndereco(obj.getEndereco());
		newObj.setDtaAdmissao(obj.getDtaAdmissao());
	}
	
	
}
