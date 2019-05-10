package com.matera.controller;

import java.util.Optional;

import com.matera.model.Funcionario;
import com.matera.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/funcionarios")
public class ControllerFuncionario {
	@Autowired
	private FuncionarioRepository funcionarioRepo;
	
	// Addcionar um funcionario
	@PostMapping(path="/oba")
	public void  adicionar(@RequestBody Funcionario funcionario) {
		funcionarioRepo.save(funcionario);
	}

	// Listar todos os funcionarios
	@GetMapping
	public @ResponseBody Iterable<Funcionario> listar() {
		return funcionarioRepo.findAll();
	}

	// buscar um funcionario unico
	@GetMapping("/{id}")
	public Funcionario buscaUnica(@PathVariable int id) {
		Optional<Funcionario> func = funcionarioRepo.findById(id);
		if(func.isPresent()) {
			Funcionario existente = func.get();
			return existente;
		}
		
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable int id) {
		Optional<Funcionario> func = funcionarioRepo.findById(id);
		if(func.isPresent()) {
			Funcionario existente = func.get();
			funcionarioRepo.delete(existente);
		}
		return ResponseEntity.noContent().build();
	}

}
