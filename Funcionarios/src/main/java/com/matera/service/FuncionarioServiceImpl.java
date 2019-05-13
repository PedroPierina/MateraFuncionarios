package com.matera.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.matera.model.Funcionario;
import com.matera.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepo;

	@Override
	public void adicionar(Funcionario funcionario) {
		funcionarioRepo.save(funcionario);
		return ;
	}

	@Override
	public Iterable<Funcionario> listar() {
		return funcionarioRepo.findAll();
	}

	@Override
	public ResponseEntity<Funcionario> buscaUnica(Integer id) {
		Optional<Funcionario> func = funcionarioRepo.findById(id);
		if (func.isPresent()) {
			Funcionario existente = func.get();
			return ResponseEntity.status(HttpStatus.OK).body(existente);
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} 
	}

	@Override
	public ResponseEntity<Funcionario> deletar(Integer id) {
		Optional<Funcionario> func = funcionarioRepo.findById(id);
		if (func.isPresent()) {
			Funcionario existente = func.get();
			funcionarioRepo.delete(existente);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

}
